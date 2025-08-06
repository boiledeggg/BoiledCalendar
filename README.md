</p>

# BoiledCalendar

### Month/Week Calendar in Android Jetpack Compose

> Boiled Calendar is a highly customizable calendar component built with Jetpack Compose, allowing full control over each part of the calendar: header, month labels, week rows, and individual day boxes.

## ✨ Features
- **Composable Architecture:** Slot-based design gives you control over the look and feel of each calendar element.

- **Flexible Headers**

   - **Calendar Header:** Customize the top section (eg. month/year switcher, navigation arrows).
   - **Month Header:** Insert weekday labels (Sun, Mon, etc.) with full styling flexibility.

- **Week Row Customization:** Change how each row of the calendar is displayed (useful for row backgrounds).

- **Day Box Customization:** Set your own day component with given day data

<br>

## 🚀 Getting Started

> you can check out sample calendar codes in [`app/src/java/com/boiled/calendar/sample`](https://github.com/boiledeggg/BoiledCalendar/tree/main/app/src/main/java/com/boiled/calendar/sample) for better understanding of using this library.

<br>

### Dependencies

Write following statement in `build.gradle.kts`
  ```kts
  implementaion("io.github.boiledeggg:boiled-calendar-compose:1.0.0")
  ```

<br>

If using Version Catalog, add following statement in `libs.versions.toml`
  ```toml
  boiled-calendar-compose = { group = "io.github.boiledeggg", name = "boiled-calendar-compose", version.ref = "1.0.0" }
  ```

<br>

## Month Calendar

### 📌 API
```kotlin
@Composable
fun MonthCalendar(
    calendarState: MonthCalendarState,
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    verticalInnerPadding: Dp = 0.dp,
    horizontalInnerPadding: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    calendarHeader: (@Composable ColumnScope.(MonthCalendarState) -> Unit)? = null,
    monthHeader: (@Composable ColumnScope.(MonthCalendarState) -> Unit)? = { WeekdaysHeader() },
    weekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)? = null,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)? = null,
)
```

✅ **calendarState:** Drives current month, start & end year of calendar

✅ **calendarHeader:** Slot for top header (e.g. month/year label, nav buttons).

✅ **monthHeader:** Weekday label row, customizable (defaults to WeekdaysHeader()).

✅ **weekBody:** Customize how each week row is structured.
 
✅ **dayBody:** Full control over rendering of each day cell (icons, badges, styling).

✅ **padding modifiers:** Fine-grained control over inner and outer spacing.

✅ **userScrollEnabled:** Enable/disable user vertical scrolling.

<br>

### 📌 MonthCalendarState
This class is an essential state class storing and maintaining data of calendar, 
including information about **months, weeks, days between starting year and ending year** of calendar, and **page which user is currently viewing**.

It is provided by `rememberMonthCalendarState()` so that it can maintain calendar information consistently throughout compose lifecycle.

```kotlin
@Composable
fun rememberMonthCalendarState(
    currentYearMonth: YearMonth = YearMonth.now(),
    startYear: Int = defaultStartYear(currentYearMonth),
    endYear: Int = defaultEndYear(currentYearMonth),
): MonthCalendarState
```

<br>

### 📌 Month Calendar Samples Code & Preview

Click [here](https://github.com/boiledeggg/BoiledCalendar/tree/main/app/src/main/java/com/boiled/calendar/sample/month) to check sample codes

| Default Month Calendar | Customized Month Calendar |
|:----------------------:|:--------------------------:|
| <img src = "https://github.com/user-attachments/assets/9e235928-6d8c-4423-8290-e8572d267043" width="300"/> | <img src = "https://github.com/user-attachments/assets/17befee1-e228-4092-b125-88914213fc78" width="300"/> |


<br>

## Week Calendar

### 📌 API
```kotlin
@Composable
fun WeekCalendar(
    calendarState: WeekCalendarState,
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    horizontalInnerPadding: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    calendarHeader: (@Composable ColumnScope.(WeekCalendarState) -> Unit)? = null,
    weekHeader: (@Composable ColumnScope.(WeekCalendarState) -> Unit)? = null,
    weekBody: (@Composable PagerScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)? = null,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)? = null,
)
```
Week Calendar API is not much different from Month Calendar API. The only difference between two apis is the state class used to manage calendar datas.

<br>

### 📌 WeekCalendarState
This state class maintains weeks in specific month. Month is determined by the date passed as parameter when creating state class object using `rememberWeekCalenarState()`.


```kotlin
@Composable
fun rememberWeekCalendarState(
    currentDate: LocalDate = LocalDate.now(),
): WeekCalendarState
```

<br>

### 📌 Week Calendar Samples Code & Preview

Click [here](https://github.com/boiledeggg/BoiledCalendar/tree/main/app/src/main/java/com/boiled/calendar/sample/week) to check sample codes

<br>

## 📄 License

MIT License

</p>
