package run.perihelion.samplemvvm

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

//from something I have on github https://github.com/Rumadon/FloatingActionMenu
class FloatingActionMenu @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {
    private val menuItems = mutableListOf<View>()
    private val mainFab: FloatingActionButton
    private val animationDuration: Long = 3000
    private var isExpanded = false

    init {
        val attr =
                context.theme.obtainStyledAttributes(attrs, R.styleable.FloatingActionButton, 0, 0)
        val mainFabDrawableRes: Int
        try {
            mainFabDrawableRes = attr.getInteger(R.styleable.FloatingActionMenu_srcMainFab, R.drawable.ic_expand_24dp)
        } finally {
            attr.recycle()
        }
        val view = View.inflate(context, R.layout.main_fab_view, this)

        mainFab = view.findViewById(R.id.main_fab)
        mainFab.setImageDrawable(ContextCompat.getDrawable(context, mainFabDrawableRes))
        mainFab.setOnClickListener { if (isExpanded) collapseMenu() else expandMenu() }
    }

    //This should be switched over to a builder
    fun addMenuItem(@DrawableRes iconDrawableRes: Int? = null, @StringRes iconLabelRes: Int? = null,
                    onItemSelected: (view: View) -> Unit): FloatingActionMenu {
        val iconDrawable = iconDrawableRes?.let {
            ContextCompat.getDrawable(context, it)
        }

        iconDrawableRes?.let {
            ContextCompat.getDrawable(context, it)
        }
        val iconLabel = iconLabelRes?.let { resources.getString(it) }
        return addMenuItem(iconDrawable, iconLabel, onItemSelected)
    }

    fun addMenuItem(
            iconDrawable: Drawable? = null,
            iconLabel: String? = null,
            onItemSelected: (view: View) -> Unit
    ): FloatingActionMenu {
        val menuDrawable: Drawable? = iconDrawable
        val menuLabelText: String? = iconLabel
        val menuItem = View.inflate(context, R.layout.list_item_fab_item, null)
        val menuItemFab = menuItem.findViewById<FloatingActionButton>(R.id.menu_item_fab)
        val menuItemLabel = menuItem.findViewById<TextView>(R.id.menu_item_label)

        menuItemLabel.text = menuLabelText ?: "".also { menuItemLabel.visibility = View.GONE }

        if (menuLabelText == null) {
            menuItemLabel.text = menuLabelText
        } else {
            menuItemLabel.visibility = View.GONE
        }

        menuItem.setOnClickListener(onItemSelected)

        menuItem.alpha = 0f

        addView(menuItem)
        menuItems.add(menuItem)
        mainFab.post {
            positionOnMainFAB(menuItem)
        }
        return this
    }

    private fun positionOnMainFAB(menuItem: View) {
        menuItem.y = (mainFab.y - (mainFab.height / 2)) + ((menuItem.height) / 2)
        menuItem.x = (mainFab.x)// + (mainFab.width / 2)) - ((menuItem.width) / 2)
    }

    fun expandMenu() {
        if (menuItems.size == 0) {
            return
        }
        val expansionSpacer = 20f
        val baseLineY: Float = mainFab.y
        for (i in 0 until menuItems.size) {
            menuItems[i].animate().setDuration(animationDuration)
                    .y(baseLineY - ((menuItems[i].height + expansionSpacer) * (i + 1))).alpha(1f)
                    .start()
        }
        isExpanded = true
    }

    fun collapseMenu() {
        if (menuItems.size == 0) {
            return
        }
        val baseLineY: Float = (mainFab.y - (mainFab.height / 2)) + ((menuItems[0].height) / 2)
        for (i in 0 until menuItems.size) {
            menuItems[i].animate().setDuration(animationDuration).y(baseLineY).alpha(0f).start()
        }
        isExpanded = false
    }

}