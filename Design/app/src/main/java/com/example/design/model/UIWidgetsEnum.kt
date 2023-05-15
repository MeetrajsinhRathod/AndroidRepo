package com.example.design.model

import com.example.design.AppBarLayoutActivity
import com.example.design.BottomNavigationActivity
import com.example.design.ButtonsActivity
import com.example.design.ChipsActivity
import com.example.design.DataBindingActivity
import com.example.design.DatePickerActivity
import com.example.design.EditTextActivity
import com.example.design.FloatingActionButtonActivity
import com.example.design.FrameLayoutActivity
import com.example.design.GridLayoutActivity
import com.example.design.ImageViewActivity
import com.example.design.ProgressActivity
import com.example.design.RadioBtnActivity
import com.example.design.RecyclerViewActivity
import com.example.design.SnackbarActivity
import com.example.design.SpinnerActivity
import com.example.design.TabLayoutActivity
import com.example.design.TextViewActivity
import com.example.design.ToastActivity
import com.example.design.ToggleSwitchActivity
import java.util.Date

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
    BottomNavigation(BottomNavigationActivity::class.java)
}