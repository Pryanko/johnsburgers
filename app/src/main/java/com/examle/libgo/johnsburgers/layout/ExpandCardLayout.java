package com.examle.libgo.johnsburgers.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.examle.libgo.johnsburgers.R;
import static com.examle.libgo.johnsburgers.tools.constants.ConstExpandCardLayout.CLOSED;
import static com.examle.libgo.johnsburgers.tools.constants.ConstExpandCardLayout.CLOSEING;
import static com.examle.libgo.johnsburgers.tools.constants.ConstExpandCardLayout.EXPANDED;
import static com.examle.libgo.johnsburgers.tools.constants.ConstExpandCardLayout.EXPANDING;
import static com.examle.libgo.johnsburgers.tools.constants.ConstExpandCardLayout.EXPAND_ANIM_DURATION;
import static com.examle.libgo.johnsburgers.tools.constants.ConstExpandCardLayout.PREINIT;

/**
 * @author libgo (19.12.2017)
 */
public class ExpandCardLayout extends CardView {

    private int expandState;
    private int expandViewHeight;
    private ValueAnimator expandAnimator;
    private ValueAnimator parentAnimator;
    private Boolean sIsInit = true;
    private Boolean expandWithParentScroll;
    private Boolean expandScrollTogether;
    private OnExpandListener onExpandListener;


    public ExpandCardLayout(Context context) {
        super(context);
        init(null);
    }

    public ExpandCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ExpandCardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs) {
        setClickable(true);
        this.setClipChildren(false);
        this.setClipToPadding(false);
        expandState = PREINIT;
        if(attrs != null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandCardLayout);
            expandWithParentScroll = typedArray.getBoolean(R.styleable.ExpandCardLayout_expWithParentScroll, false);
            expandScrollTogether = typedArray.getBoolean(R.styleable.ExpandCardLayout_expExpandScrollTogether, false);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(sIsInit){
            ((MarginLayoutParams) getChildAt(0).getLayoutParams()).bottomMargin = 0;
            MarginLayoutParams marginLayoutParams = ((MarginLayoutParams) getChildAt(1).getLayoutParams());
            marginLayoutParams.bottomMargin = 0;
            marginLayoutParams.topMargin = 0;
            marginLayoutParams.height = 0;
            expandViewHeight = getChildAt(1).getMeasuredHeight();
            sIsInit = false;
            expandState = CLOSED;
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void verticalAnimate(final int startHeight, final int endHeight){
        final ViewGroup viewParent = (ViewGroup) getParent();
        int distance = (int) (getY() + getMeasuredHeight() + expandViewHeight - viewParent.getMeasuredHeight());
        final View target = getChildAt(1);
        expandAnimator = ValueAnimator.ofInt(startHeight, endHeight);
        expandAnimator.addUpdateListener(valueAnimator -> {
            target.getLayoutParams().height = (int) valueAnimator.getAnimatedValue();
            target.requestLayout();
        });

        expandAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (endHeight - startHeight < 0){
                    expandState = CLOSED;
                    if(onExpandListener != null){
                        onExpandListener.onExpand(false);
                    }
                }else {
                    expandState = EXPANDED;
                    if(onExpandListener != null){
                        onExpandListener.onExpand(true);
                    }
                }
            }
        });

        expandState = expandState == EXPANDED ? CLOSEING : EXPANDING;
        expandAnimator.setDuration(EXPAND_ANIM_DURATION);
        if(expandState == EXPANDING && expandWithParentScroll && distance > 0){
            expandAnimator = parentScroll(distance);
            AnimatorSet animatorSet = new AnimatorSet();
            if(expandScrollTogether){
                animatorSet.playSequentially(expandAnimator, parentAnimator);
            }else {
                animatorSet.playTogether(expandAnimator, parentAnimator);
            }
            animatorSet.start();
        }else {
            expandAnimator.start();
        }
    }
    private ValueAnimator parentScroll(final int distance) {
        final ViewGroup viewGroup = (ViewGroup) getParent();
        parentAnimator = ValueAnimator.ofInt(0, distance);
        parentAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int lastDy, dy;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dy = (int) valueAnimator.getAnimatedValue() - lastDy;
                lastDy = (int) valueAnimator.getAnimatedValue();
                viewGroup.scrollBy(0, dy);
            }
        });
        parentAnimator.setDuration(EXPAND_ANIM_DURATION);
        return expandAnimator;
    }

    public void setExpand(boolean expand){
        if(expandState == PREINIT){
            return;
        }
        getChildAt(1).getLayoutParams().height = expand ? expandViewHeight : 0;
        requestLayout();
        expandState = expand ? EXPANDED : CLOSED;
    }

    public void toggle(){
        if(expandState == EXPANDED){
            close();
        }
        if(expandState == CLOSED){
            expand();
        }
    }

    private void expand() {
        verticalAnimate(0, expandViewHeight);
    }

    private void close() {
        verticalAnimate(expandViewHeight, 0);
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public void setOnExpandListener(OnExpandListener onExpandListener){
        this.onExpandListener = onExpandListener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(expandAnimator != null && expandAnimator.isRunning()){
            expandAnimator.cancel();
        }
        if(parentAnimator != null && parentAnimator.isRunning()){
            parentAnimator.cancel();
        }
    }
}



 /*  public void setExpandScrollTogether(boolean expandScrollTogether){
        this.expandScrollTogether = expandScrollTogether;
    }

   public void setExpandWithParentScroll(boolean expandWithParentScroll){
        this.expandWithParentScroll = expandWithParentScroll;
   }

   public  void setExpandAnimDuration(int expandAnimDuration){
   }

   public boolean isExpanded(){
        return expandState == EXPANDED;
   }*/
 //Для будущих преобразований анимации
