package com.qdot.mathrendererlib

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Base64
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import kotlin.properties.Delegates

class MathRenderView : WebView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    @SuppressLint("SetJavaScriptEnabled")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        with(settings) {
            loadWithOverviewMode = true
            javaScriptEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE
        }
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        if (attrs != null) {
            val math = context.obtainStyledAttributes(attrs, R.styleable.MathRenderView)
            if (math.hasValue(R.styleable.MathRenderView_text)) {
                this.text = math.getString(R.styleable.MathRenderView_text)
            }
            if (math.hasValue(R.styleable.MathRenderView_textColor)){
                this.textColor = math.getString(R.styleable.MathRenderView_textColor)
            }
            if (math.hasValue(R.styleable.MathRenderView_mathBackgroundColor)){
                this.mathBackgroundColor = math.getString(R.styleable.MathRenderView_mathBackgroundColor)
            }
            math.recycle()
        }
    }

    var text: String? by Delegates.observable("") { _, old, new ->
        if (old != new) {
            doRender()
        }
    }

    var textAlignment: TextAlign by Delegates.observable(TextAlign.START) { _, old, new ->
        if (old != new) {
            doRender()
        }
    }

    var textColor: String? by Delegates.observable("#000000") { _, old, new ->
        if (old != new) {
            doRender()
        }
    }

    var mathBackgroundColor: String? by Delegates.observable("#FFFFFF") { _, old, new ->
        if (old != new) {
            doRender()
        }
    }

    private fun doRender(){
        val p1 = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width\">"+
                "<title>MathJax TeX Test Page</title>\n" +
                "<script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>\n" +
                "<script type=\"text/javascript\" id=\"MathJax-script\" async\n" +
                "  src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\">\n" +
                "</script>\n" +"<script>"+
                "MathJax = {\n" +
                "  options: {\n" +
                "    enableMenu: false" + "}\n" +
                "};"+
                "</script>"+
                "</head>\n" +
                "<body style=\"background-color:" +
                mathBackgroundColor.toString().uppercase() + ";\">" +
                "<p style=\"text-align:" +
                textAlignment.toString().lowercase() +
                ";" + "color:" + textColor.toString().uppercase() + ";\">"
        val p2 = "</p>" + "</body>\n" +
                "</html>"
        val fullMathText = p1 + text + p2
        val base64 = Base64.encodeToString(fullMathText.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        render(base64)
    }
    private fun render(base64: String) = loadUrl("data:text/html;charset=utf-8;base64,$base64")
}

enum class TextAlign {
    CENTER, START, END
}