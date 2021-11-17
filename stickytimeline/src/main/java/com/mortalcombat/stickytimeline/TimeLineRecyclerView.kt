package com.mortalcombat.stickytimeline

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TimeLineRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    private var recyclerViewAttr: RecyclerViewAttr? = null

    companion object {
        private const val MODE_VERTICAL = 0x00
        private const val MODE_HORIZONTAL = 0x01
    }

    init {
        attrs?.let {
            val a = context.theme?.obtainStyledAttributes(
                attrs,
                R.styleable.TimeLineRecyclerView,
                0, 0
            )

            a?.let {
                recyclerViewAttr =
                    RecyclerViewAttr(
                        it.getColor(
                            R.styleable.TimeLineRecyclerView_sectionBackgroundColor,
                            ContextCompat.getColor(context, R.color.purple_500)
                        ),
                        it.getColor(
                            R.styleable.TimeLineRecyclerView_sectionTitleTextColor,
                            ContextCompat.getColor(context, R.color.white)
                        ),
                        it.getColor(
                            R.styleable.TimeLineRecyclerView_sectionSubTitleTextColor,
                            ContextCompat.getColor(context, R.color.white)
                        ),
                        it.getColor(
                            R.styleable.TimeLineRecyclerView_timeLineColor,
                            ContextCompat.getColor(context, R.color.purple_500)
                        ),
                        it.getColor(
                            R.styleable.TimeLineRecyclerView_timeLineCircleColor,
                            ContextCompat.getColor(context, R.color.colorDefaultTitle)
                        ),
                        it.getColor(
                            R.styleable.TimeLineRecyclerView_timeLineCircleStrokeColor,
                            ContextCompat.getColor(context, R.color.colorDefaultStroke)
                        ),
                        it.getDimension(
                            R.styleable.TimeLineRecyclerView_sectionTitleTextSize,
                            context.resources.getDimension(R.dimen.sub_title_text_size)
                        ),
                        it.getDimension(
                            R.styleable.TimeLineRecyclerView_sectionSubTitleTextSize,
                            context.resources.getDimension(R.dimen.sub_title_text_size)
                        ),
                        it.getDimension(
                            R.styleable.TimeLineRecyclerView_timeLineWidth,
                            context.resources.getDimension(R.dimen.line_width)
                        ),
                        it.getBoolean(R.styleable.TimeLineRecyclerView_isSticky, true),
                        it.getDrawable(R.styleable.TimeLineRecyclerView_customDotDrawable),
                        it.getInt(R.styleable.TimeLineRecyclerView_mode, 0)
                    )

            }

        }
    }

    /**
     * Add VerticalSectionItemDecoration for Sticky TimeLineView
     *
     * @param callback SectionCallback
     * if you'd like to know more mode , look at res/values/attrs.xml
     */
    fun addItemDecoration(callback: VerticalSectionItemDecoration.SectionCallback) {
        recyclerViewAttr?.let {
            val decoration: ItemDecoration =
                when (it.mode) {
                    MODE_VERTICAL -> {
                        VerticalSectionItemDecoration(
                            context,
                            callback,
                            it
                        )
                    }
                    MODE_HORIZONTAL -> {
                        HorizontalSectionItemDecoration(
                            context,
                            callback,
                            it
                        )
                    }
                    else -> {
                        VerticalSectionItemDecoration(
                            context,
                            callback,
                            it
                        )
                    }
                }
            this.addItemDecoration(decoration)
        }
    }
}
