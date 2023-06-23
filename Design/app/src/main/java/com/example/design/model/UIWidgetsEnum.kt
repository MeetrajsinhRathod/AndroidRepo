package com.example.design.model

import com.example.design.activity.CoughMedicineActivity
import com.example.design.activity.UserProfileActivity
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
import com.example.design.activity.NavigationActivity
import com.example.design.activity.ProgressActivity
import com.example.design.activity.RadioBtnActivity
import com.example.design.activity.RecyclerViewActivity
import com.example.design.activity.RecyclerViewKtActivity
import com.example.design.activity.SearchViewActivity
import com.example.design.activity.SnackbarActivity
import com.example.design.activity.SpinnerActivity
import com.example.design.activity.TabLayoutActivity
import com.example.design.activity.TextViewActivity
import com.example.design.activity.ToastActivity
import com.example.design.activity.ToggleSwitchActivity
import com.example.webService.activity.WebServicesActivity
import com.example.design.activity.WebViewActivity
import com.example.onecloud.modules.login.activity.OneCloudOnBoardActivity

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
    Intents(IntentsActivity::class.java),
    WebView(WebViewActivity::class.java),
    SearchView(SearchViewActivity::class.java),
    NavigationComponent(NavigationActivity::class.java),
    UIWidgetsKTMedicineScreen(CoughMedicineActivity::class.java),
    OneCloudAccountSecurity(com.example.design.activity.OneCloudAccountSecurity::class.java),
    UserProfile(UserProfileActivity::class.java),
    RecyclerViewKT(RecyclerViewKtActivity::class.java),
    WebServices(WebServicesActivity::class.java),
    OneCloudLogin(OneCloudOnBoardActivity::class.java)
}