package com.smile.taobaodemo.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.smile.taobaodemo.R;

public class SlideDetailsLayout extends ViewGroup {

    /**
     * Callback for panel OPEN-CLOSE status changed.
     */
    public interface OnSlideDetailsListener {
        /**
         * Called after status changed.
         *
         * @param status {@link Status}
         */
        void onStatusChanged(Status status);
    }

    public enum Status {
        /**
         * Panel is closed
         */
        CLOSE,
        /**
         * Panel is opened
         */
        OPEN;

        public static Status valueOf(int status) {
            if (0 == status) {
                return CLOSE;
            } else if (1 == status) {
                return OPEN;
            } else {
                return CLOSE;
            }
        }
    }

    private static final float DEFAULT_PERCENT = 0.2f;
    private static final int DEFAULT_DURATION = 300;
    private static final float DEFAULT_MAX_VELOCITY = 2500f;

    private View frontView;
    private View behindView;

    private float touchSlop;
    private float initMotionY;
    private float initMotionX;


    private View target;
    private float slideOffset;
    private Status status = Status.CLOSE;
    private boolean isFirstShowBehindView = true;
    private float percent = DEFAULT_PERCENT;
    private long duration = DEFAULT_DURATION;
    private int defaultPanel = 0;
    private VelocityTracker velocityTracker;

    private OnSlideDetailsListener listener;

    public SlideDetailsLayout(Context context) {
        this(context, null);
    }

    public SlideDetailsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideDetailsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlideDetailsLayout, defStyleAttr, 0);
        percent = a.getFloat(R.styleable.SlideDetailsLayout_percent, DEFAULT_PERCENT);
        duration = a.getInt(R.styleable.SlideDetailsLayout_duration, DEFAULT_DURATION);
        defaultPanel = a.getInt(R.styleable.SlideDetailsLayout_default_panel, 0);
        a.recycle();

        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    /**
     * Set the callback of panel OPEN-CLOSE status.
     *
     * @param listener {@link OnSlideDetailsListener}
     */
    public void setOnSlideDetailsListener(OnSlideDetailsListener listener) {
        this.listener = listener;
    }

    /**
     * Open pannel smoothly.
     *
     * @param smooth true, smoothly. false otherwise.
     */
    public void smoothOpen(boolean smooth) {
        if (status != Status.OPEN) {
            status = Status.OPEN;
            final float height = -getMeasuredHeight();
            animatorSwitch(0, height, true, smooth ? duration : 0);
        }
    }

    /**
     * Close pannel smoothly.
     *
     * @param smooth true, smoothly. false otherwise.
     */
    public void smoothClose(boolean smooth) {
        if (status != Status.CLOSE) {
            status = Status.OPEN;
            final float height = -getMeasuredHeight();
            animatorSwitch(height, 0, true, smooth ? duration : 0);
        }
    }

    /**
     * Set the float value for indicate the moment of switch panel
     *
     * @param percent (0.0, 1.0)
     */
    public void setPercent(float percent) {
        this.percent = percent;
    }

    public class LayoutParams extends MarginLayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }


    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (1 >= childCount) {
            throw new RuntimeException("SlideDetailsLayout only accept childs more than 1!!");
        }

        frontView = getChildAt(0);
        behindView = getChildAt(1);

        // set behindview's visibility to GONE before show.
        behindView.setVisibility(GONE);
        if (defaultPanel == 1) {
            post(new Runnable() {
                @Override
                public void run() {
                    smoothOpen(false);
                }
            });
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int height = MeasureSpec.getSize(heightMeasureSpec);

         int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
         int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            measureChild(child, childWidthMeasureSpec, childHeightMeasureSpec);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top;
        int bottom;

        final int offset = (int) slideOffset;

        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            if (child == behindView) {
                top = b + offset;
                bottom = top + b - t;
            } else {
                top = t + offset;
                bottom = b + offset;
            }

            child.layout(l, top, r, bottom);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ensureTarget();
        if (null == target) {
            return false;
        }

        if (!isEnabled()) {
            return false;
        }

        final int action = MotionEventCompat.getActionMasked(ev);

        boolean shouldIntercept = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                if (null == velocityTracker) {
                    velocityTracker = VelocityTracker.obtain();
                } else {
                    velocityTracker.clear();
                }
                velocityTracker.addMovement(ev);

                initMotionX = ev.getX();
                initMotionY = ev.getY();
                shouldIntercept = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();

                final float xDiff = x - initMotionX;
                final float yDiff = y - initMotionY;

                if (canChildScrollVertically((int) yDiff)) {
                    shouldIntercept = false;
                } else {
                    final float xDiffabs = Math.abs(xDiff);
                    final float yDiffabs = Math.abs(yDiff);

                    // intercept rules：
                    // 1. The vertical displacement is larger than the horizontal displacement;
                    // 2. Panel stauts is CLOSE：slide up
                    // 3. Panel status is OPEN：slide down
                    if (yDiffabs > touchSlop && yDiffabs >= xDiffabs
                            && !(status == Status.CLOSE && yDiff > 0
                            || status == Status.OPEN && yDiff < 0)) {
                        shouldIntercept = true;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                shouldIntercept = false;
                break;
            }

        }

        return shouldIntercept;
    }

    private void recycleVelocityTracker() {
        if (null != velocityTracker) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        ensureTarget();
        if (null == target) {
            return false;
        }

        if (!isEnabled()) {
            return false;
        }


        boolean wantTouch = true;
        final int action = MotionEventCompat.getActionMasked(ev);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                // if target is a view, we want the DOWN action.
                if (target instanceof View) {
                    wantTouch = true;
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                velocityTracker.addMovement(ev);
                velocityTracker.computeCurrentVelocity(1000);
                final float y = ev.getY();
                final float yDiff = y - initMotionY;
                if (canChildScrollVertically(((int) yDiff))) {
                    wantTouch = false;
                } else {
                    processTouchEvent(yDiff);
                    wantTouch = true;
                }
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                finishTouchEvent();
                recycleVelocityTracker();
                wantTouch = false;
                break;
            }
        }
        return wantTouch;
    }

    /**
     * @param offset Displacement in vertically.
     */
    private void processTouchEvent(final float offset) {
        if (Math.abs(offset) < touchSlop) {
            return;
        }

        final float oldOffset = slideOffset;
        // pull up to open
        if (status == Status.CLOSE) {
            // reset if pull down
            if (offset >= 0) {
                slideOffset = 0;
            } else {
                slideOffset = offset;
            }

            if (slideOffset == oldOffset) {
                return;
            }

            // pull down to close
        } else if (status == Status.OPEN) {
            final float pHeight = -getMeasuredHeight();
            // reset if pull up
            if (offset <= 0) {
                slideOffset = pHeight;
            } else {
                slideOffset = pHeight + offset;
            }

            if (slideOffset == oldOffset) {
                return;
            }
        }
        requestLayout();
    }

    /**
     * Called after gesture is ending.
     */
    private void finishTouchEvent() {
        final int height = getMeasuredHeight();
        final int perHeight = (int) (height * percent);
        final float offset = slideOffset;

        boolean changed = false;
        final float yVelocity = velocityTracker.getYVelocity();

        if (Status.CLOSE == status) {
            if (offset <= -perHeight || yVelocity <= -DEFAULT_MAX_VELOCITY) {
                slideOffset = -height;
                status = Status.OPEN;
                changed = true;
            } else {
                // keep panel closed
                slideOffset = 0;
            }
        } else if (Status.OPEN == status) {
            if ((offset + height) >= perHeight || yVelocity >= DEFAULT_MAX_VELOCITY) {
                slideOffset = 0;
                status = Status.CLOSE;
                changed = true;
            } else {
                // keep panel opened
                slideOffset = -height;
            }
        }

        animatorSwitch(offset, slideOffset, changed);
    }

    private void animatorSwitch(final float start, final float end) {
        animatorSwitch(start, end, true, duration);
    }

    private void animatorSwitch(final float start, final float end, final long duration) {
        animatorSwitch(start, end, true, duration);
    }

    private void animatorSwitch(final float start, final float end, final boolean changed) {
        animatorSwitch(start, end, changed, duration);
    }

    private void animatorSwitch(final float start,
                                final float end,
                                final boolean changed,
                                final long duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                slideOffset = (float) animation.getAnimatedValue();
                requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (changed) {
                    if (status == Status.OPEN) {
                        checkAndFirstOpenPanel();
                    }

                    if (null != listener) {
                        listener.onStatusChanged(status);
                    }
                }
            }
        });
        animator.setDuration(duration);
        animator.start();
    }

    /**
     * Whether the closed pannel is opened at first time.
     * If open first, we should set the behind view's visibility as VISIBLE.
     */
    private void checkAndFirstOpenPanel() {
        if (isFirstShowBehindView) {
            isFirstShowBehindView = false;
            behindView.setVisibility(VISIBLE);
        }
    }

    /**
     * When pulling, target view changed by the panel status. If panel opened, the target is behind view.
     * Front view is for otherwise.
     */
    private void ensureTarget() {
        if (status == Status.CLOSE) {
            target = frontView;
        } else {
            target = behindView;
        }
    }

    /**
     * Check child view can srcollable in vertical direction.
     *
     * @param direction Negative to check scrolling up, positive to check scrolling down.
     * @return true if this view can be scrolled in the specified direction, false otherwise.
     */
    protected boolean canChildScrollVertically(int direction) {
        return innerCanChildScrollVertically(target, -direction);
    }

    private boolean innerCanChildScrollVertically(View view, int direction) {
        if (view instanceof ViewGroup) {
            final ViewGroup vGroup = (ViewGroup) view;
            View child;
            boolean result;
            for (int i = 0; i < vGroup.getChildCount(); i++) {
                child = vGroup.getChildAt(i);
                result = ViewCompat.canScrollVertically(child, direction);
                if (child instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) child;
                    View ch;
                    for (int j = 0; j < viewGroup.getChildCount(); j++) {
                        ch = viewGroup.getChildAt(j);
                        result = ViewCompat.canScrollVertically(ch, direction);
                        if (ch instanceof ViewGroup) {
                            ViewGroup group = (ViewGroup) ch;
                            View chi;
                            for (int k = 0; k < group.getChildCount(); k++) {
                                chi = group.getChildAt(k);
                                result = ViewCompat.canScrollVertically(chi, direction);
                                if (result)
                                    return true;
                            }
                        }
                        if (result) {
                            return true;
                        }
                    }
                }
                if (result) {
                    return true;
                }
            }
        }

        return ViewCompat.canScrollVertically(view, direction);
    }

    protected boolean canListViewSroll(AbsListView absListView) {
        if (status == Status.OPEN) {
            return absListView.getChildCount() > 0
                    && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                    .getTop() <
                    absListView.getPaddingTop());
        } else {
            final int count = absListView.getChildCount();
            return count > 0
                    && (absListView.getLastVisiblePosition() < count - 1
                    || absListView.getChildAt(count - 1)
                    .getBottom() > absListView.getMeasuredHeight());
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.offset = slideOffset;
        ss.status = status.ordinal();
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        slideOffset = ss.offset;
        status = Status.valueOf(ss.status);

        if (status == Status.OPEN) {
            behindView.setVisibility(VISIBLE);
        }

        requestLayout();
    }

    static class SavedState extends BaseSavedState {

        private float offset;
        private int status;

        /**
         * Constructor used when reading from a parcel. Reads the state of the superclass.
         *
         * @param source
         */
        SavedState(Parcel source) {
            super(source);
            offset = source.readFloat();
            status = source.readInt();
        }

        /**
         * Constructor called by derived classes when creating their SavedState objects
         *
         * @param superState The state of the superclass of this view
         */
        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeFloat(offset);
            out.writeInt(status);
        }

        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
