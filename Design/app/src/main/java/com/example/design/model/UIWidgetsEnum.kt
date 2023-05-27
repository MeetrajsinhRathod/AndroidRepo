package com.example.design.model

import com.example.design.activity.AppBarLayoutActivity
import com.example.design.activity.BottomNavigationActivity
import com.example.design.activity.ButtonsActivity
import com.example.design.activity.ChipsActivity
import com.example.design.activity.DataBindingActivity
import com.example.design.activity.DatePickerActivity
import com.example.design.activity.EditTextActivity
import com.example.design.activity.FloatingActionButtonActivity
import com.example.design.activity.FragmentActivity
import com.example.design.activity.FrameLayoutActivity
import com.example.design.activity.GridLayoutActivity
import com.example.design.activity.ImageViewActivity
import com.example.design.activity.IntentsActivity
import com.example.design.activity.ProgressActivity
import com.example.design.activity.RadioBtnActivity
import com.example.design.activity.RecyclerViewActivity
import com.example.design.activity.SnackbarActivity
import com.example.design.activity.SpinnerActivity
import com.example.design.activity.TabLayoutActivity
import com.example.design.activity.TextViewActivity
import com.example.design.activity.ToastActivity
import com.example.design.activity.ToggleSwitchActivity

enum class UIWidgetsEnum(val cls: Class<*>) {
    TextView(TextViewActivity::class.java),
    EditText(EditTextActivity::class.java),
    Buttons(ButtonsActivity::class.java),
    RadioButton_CheckBox(RadioBtnActivity::class.java),
    ToggleButton_Switch(ToggleSwitchActivity::class.java),
    ImageView(ImageViewActivity::class.java),
    Toast(ToastActivity::class.java),
    ProgressBar_SeekBar(ProgressActivity::class.java),
    Chips(ChipsActivity::class.java),
    Spinner(SpinnerActivity::class.java),
    DatePicker(DatePickerActivity::class.java),
    FloatingActionButton(FloatingActionButtonActivity::class.java),
    SnackBar(SnackbarActivity::class.java),
    DataBinding(DataBindingActivity::class.java),
    FrameLayout(FrameLayoutActivity::class.java),
    TabLayout(TabLayoutActivity::class.java),
    GridLayout(GridLayoutActivity::class.java),
    AppBarLayout(AppBarLayoutActivity::class.java),
    RecyclerView(RecyclerViewActivity::class.java),
    BottomNavigation(BottomNavigationActivity::class.java),
    Fragment(FragmentActivity::class.java),
    Intents(IntentsActivity::class.java)
}