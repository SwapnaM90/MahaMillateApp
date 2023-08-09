package com.mninetytechnology.mahamillateapp.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.mninetytechnology.mahamillateapp.R;

public class RoundedCornerEditText extends LinearLayout {
    private boolean passwordToggleEnabled = false;
    private String my_text;
    private Context mContext;
    private EditText mEditText;
    private ImageView mImageView_View;
    private ImageView mImageView_Hide;
    private String hint;

    private int my_inputType = EditorInfo.TYPE_NULL;

    private int max = -1;
    private int min = -1;

    public RoundedCornerEditText(Context context) {
        super(context);
        init(context,null,0);
    }

    public RoundedCornerEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public RoundedCornerEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    public RoundedCornerEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.mContext = context;
        inflate(context, R.layout.rounded_corner_edittext_layout,this);
        mEditText = findViewById(R.id.edt);
        mImageView_View = findViewById(R.id.img_view);
        mImageView_Hide = findViewById(R.id.img_hide);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundedCornerEditText, defStyle, 0);
            passwordToggleEnabled = a.getBoolean(R.styleable.RoundedCornerEditText_passwordToggleEnabled,false);
            my_text = a.getString(R.styleable.RoundedCornerEditText_my_text);
            hint = a.getString(R.styleable.RoundedCornerEditText_hint);
            my_inputType = a.getInt(R.styleable.RoundedCornerEditText_my_inputType, EditorInfo.TYPE_NULL);
            max = a.getInt(R.styleable.RoundedCornerEditText_max,-1);
            min = a.getInt(R.styleable.RoundedCornerEditText_min,-1);
            a.recycle();
        }
        setupView();
    }

    private void setupView() {
        mEditText.setText(my_text);
        mEditText.setHint(hint);
        if (my_inputType != EditorInfo.TYPE_NULL) {
            mEditText.setInputType(my_inputType);
        }
        if (max != -1) {
            setEditTextMaxLength(max);
        }
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                my_text = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //mImageView.setColorFilter(passwordToggleTintColor);

        if (passwordToggleEnabled) {
            mImageView_View.setVisibility(VISIBLE);
            mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mImageView_Hide.setVisibility(GONE);
            mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

            mImageView_View.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mImageView_Hide.setVisibility(VISIBLE);
                    mImageView_View.setVisibility(GONE);
                    mEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            });

            mImageView_Hide.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mImageView_View.setVisibility(VISIBLE);
                    mImageView_Hide.setVisibility(GONE);
                    mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            });
        } else {
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            mImageView_View.setVisibility(GONE);
            mImageView_Hide.setVisibility(GONE);
        }
    }

    public String getMy_text() {
        return my_text;
    }

    public void setMy_text(String my_text) {
        this.my_text = my_text;
    }

    @InverseBindingAdapter(attribute = "my_text")
    public static String getMy_text(RoundedCornerEditText view) {
        return view.my_text;
    }

    @BindingAdapter("my_text")
    public static void setMy_text(RoundedCornerEditText view,String my_text) {
        view.my_text = my_text;
    }

    @InverseBindingAdapter(attribute = "my_inputType")
    public int getMy_inputType(RoundedCornerEditText view) {
        return view.my_inputType;
    }

    @BindingAdapter("my_inputType")
    public void setMy_inputType(RoundedCornerEditText view,int my_inputType) {
        view.my_inputType = my_inputType;
    }

    @BindingAdapter("app:my_textAttrChanged")
    public static void setListeners(RoundedCornerEditText view, final InverseBindingListener attrChange) {
        // Set a listener for click, focus, touch, etc.
        view.mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= view.min) {
                    attrChange.onChange();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setEditTextMaxLength(int length) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        mEditText.setFilters(filterArray);
    }

}
