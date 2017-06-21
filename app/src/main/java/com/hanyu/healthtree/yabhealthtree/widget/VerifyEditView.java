package com.hanyu.healthtree.yabhealthtree.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.hanyu.healthtree.yabhealthtree.R;

/**
 * Created by xumingming on 2017/6/21.
 */
public class VerifyEditView extends EditText {

    private int mCodeLength;  //字体的长度
    private int mCodeColor;  //字体的颜色  默认是白色
    private int mCodeSize; //字体的大小

    private int mCodeInterval; //字体之间的间隔

    private int mCodeBackgroundColor;  //字体的背景颜色

    private Paint mPaint;
    private Paint mBackGroundPaint;
    private int mWidth;
    private int mHeight;
    private RectF mRectF;

    private String mTextContent;
    private int mTextLength;

    //当不需要使用xml声明或者不需要使用inflate动态加载时候，实现此构造函数即可
    public VerifyEditView(Context context) {
        this(context,null);
    }
    //在xml创建但是没有指定style的时候被调用
    public VerifyEditView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    //有指定style的时候被调用（注：虽然我将代码写在这地方，但是我并没有指定style，事实上，程序会使用第二个构造函数，只是将第三个参数传0而已）
    public VerifyEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们在attrs定义的自定义属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VerifyEditView,defStyleAttr,0);



        mCodeColor = a.getColor(R.styleable.VerifyEditView_VerifyCodeTextColor,Color.WHITE);

        mCodeBackgroundColor = a.getColor(R.styleable.VerifyEditView_VerifyCodeBackResoure,Color.BLACK);

        mCodeLength = a.getInt(R.styleable.VerifyEditView_VerifyCodeLength,4);

        //第二个参数为默认值，当我们在使用此控件但是没有定义此属性值的时候，会使用此处设置的默认值
        mCodeInterval = a.getDimensionPixelSize(R.styleable.VerifyEditView_VerifyCodeInterval,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,10,getResources().getDisplayMetrics()));

        mCodeSize = a.getDimensionPixelSize(R.styleable.VerifyEditView_VerifyCodeTextSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,24,getResources().getDisplayMetrics()));
        a.recycle();
        //定义一个Paint，并支持抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackGroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mCodeLength)}); //最大输入长度
    }


    //在绘制控件时候会自动调用
    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getWidth();//获取控件的宽度
        mHeight = getHeight();//获取控件的高度
        mRectF = new RectF(0,0,mWidth,mHeight);

        //分割线
        float offset = mRectF.width() / mCodeLength;

        //绘制文字的背景
        for (int i=0;i<mCodeLength;i++){
            RectF rectF = new RectF(mRectF.left + offset*i + mCodeInterval / 2, mRectF.top,
                    mRectF.left + offset*(i+1) - mCodeInterval / 2, mRectF.bottom);
            canvas.drawRoundRect(rectF,
                    5,5,mBackGroundPaint);
        }

        mPaint.setColor(mCodeColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mCodeSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        mBackGroundPaint.setColor(mCodeBackgroundColor);
        mBackGroundPaint.setStyle(Paint.Style.FILL);

        float baseLine = (mRectF.bottom + mRectF.top - fontMetrics.bottom - fontMetrics.top)/2;
        float codeX;
        //更新数字 和背景
        for (int i=0;i<mTextLength;i++){
            codeX = mRectF.left + offset * i + offset / 2;
            canvas.drawText(mTextContent.substring(i,i+1),codeX,baseLine,mPaint);
        }

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.mTextContent = text.toString();
        this.mTextLength = text.toString().length();

        if (mTextLength>mCodeLength ){
            //当文字的长度大于我们需要的长度  就不需要重绘了
            return;
        }
        invalidate();//当text改变的时候，重新绘制控件
    }

    /**
     * 重写 onMeasure 支持 wrap_content 日后完善
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
