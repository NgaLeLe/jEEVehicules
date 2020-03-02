function log(message, obj) {
	obj = obj || {};
	console.log(message, obj);
}

var Schedule = {

	Manager : {
		Controller : {},
		Helpers : {},
		ViewSwitcher : {},
		DayViewBuilder : {},
		WeekViewBuilder : {},
		MonthViewBuilder : {},
		DayViewUpdater : {},
		WeekViewUpdater : {},
		MonthViewUpdater : {}
	},
	Navigation : {},
	EventManager : {},
	HeaderControl : {},

	ActiveDate : ""
};

Schedule.Navigation.Controller = (function() {
	var history = {}, historyIndex = 0;
	var $btn;

	function init() {
		$btn = $("#sch-go-back-btn");
	}

	function addNavigationEvent(viewStr, utcDate, blnHideBtn) {
		console.log("addNavigationEvent", arguments);
		historyIndex += 1;
		history[historyIndex] = {
			view : viewStr,
			date : utcDate
		};

		if (blnHideBtn === true)
			return;

		$btn.addClass("active");
	}

	function isMonthDifferent(date1, date2) {
		return date1.getMonth() != date2.getMonth();
	}

	function goBack() {
		console.log("go back", history);
		if (historyIndex < 2)
			return;

		historyIndex -= 1;

		console.log("history index: " + historyIndex);
		if (historyIndex <= 1) {
			$btn.removeClass("active");
		}

		var prev = history[historyIndex];

		var isEdgeMonth = isMonthDifferent(Schedule.ActiveDate, prev.date);
		Schedule.Manager.Helpers.setActiveDate(prev.date, isEdgeMonth);

		var switcher = Schedule.Manager.ViewSwitcher;
		switch (prev.view) {
		case "day":
			switcher.switchToDay();
			break;
		case "week":
			switcher.switchToWeek();
			break;

		default:
			console.error("Go Back: unknown view encountered: ", prev);
		}
	}

	return {
		init : init,
		addNavigationEvent : addNavigationEvent,
		goBack : goBack
	};
})();

Schedule.Manager.Controller = (function() {
	var helpers;

	function start() {
		helpers = Schedule.Manager.Helpers;

		Schedule.HeaderControl.init();
		Schedule.Navigation.Controller.init();

		Schedule.Manager.DayViewUpdater.init();
		Schedule.Manager.DayViewBuilder.buildView();

		Schedule.Manager.WeekViewUpdater.init();
		Schedule.Manager.WeekViewBuilder.buildView();

		Schedule.Manager.MonthViewUpdater.init();
		Schedule.Manager.MonthViewBuilder.buildView();

		// test code
		$("#goToDate").on(
				"click",
				function() {
					// console.log($('#datepicker').val());
					var date = new Date($("#datepicker").val());
					var utcDate = Schedule.Manager.Helpers
							.convertDateToUTC(date);

					Schedule.Navigation.Controller.addNavigationEvent("month",
							utcDate);
					Schedule.Manager.Helpers.setActiveDate(utcDate);
				});

		var now = new Date();
		var nowMonth = now.getMonth() + 1;
		var nowDate = now.getDate();
		var dateStr = [
				now.getFullYear(),
				nowMonth.toString().length > 1 ? nowMonth : [ "0", nowMonth ]
						.join(""),
				nowDate.toString().length > 1 ? nowDate : [ "0", nowDate ]
						.join("") ].join("-");

		$("#datepicker").val(dateStr);

		// set initial date
		firstDateSet();

		var $monthCells = $("#month-view .cell:not(.header)");

		$monthCells.on("click", function() {
			var $cell = $(this);
			var $day = $cell.find(".day");
			var isEdgeBefore = $day.hasClass("before");
			var isEdgeAfter = $day.hasClass("after");
			console.log("isEdgeBefore: " + isEdgeBefore.toString());
			console.log("isEdgeAfter: " + isEdgeAfter.toString());

			var selectedDate = parseInt($day.find("span").html());
			var utcDate = Schedule.Manager.Helpers.buildNewDateFromSelection(
					selectedDate, isEdgeBefore, isEdgeAfter);

			if (isEdgeBefore || isEdgeAfter) {
				Schedule.Navigation.Controller.addNavigationEvent("month",
						utcDate);
			}

			Schedule.Manager.Helpers.setActiveDate(utcDate, isEdgeBefore,
					isEdgeAfter);
		});

		var $weekCells = $("#week-view .cell.header");
		$weekCells.on("click",
				function() {
					var $dayHeader = $(this);

					var smallDate = $dayHeader.find("span").html();
					var slashIndex = smallDate.indexOf("/");
					var dateStr = smallDate.substring(slashIndex + 1,
							smallDate.length);

					var selectedMonthIndex = parseInt(monthStr) - 1;
					var selectedDate = parseInt(dateStr) || 1;

					var isEdgeBefore = selectedMonthIndex < activeMonthIndex;
					var isEdgeAfter = selectedMonthIndex > activeMonthIndex;

					var utcDate = Schedule.Manager.Helpers
							.buildNewDateFromSelection(selectedDate,
									isEdgeBefore, isEdgeAfter);

					Schedule.Navigation.Controller.addNavigationEvent("day",
							utcDate);
					Schedule.Manager.Helpers.setActiveDate(utcDate);
					Schedule.Manager.ViewSwitcher.switchToDay();
				});
		// end test code
	}

	function firstDateSet() {
		var date = new Date($("#datepicker").val());
		var utcDate = Schedule.Manager.Helpers.convertDateToUTC(date);

		Schedule.Manager.Helpers.setActiveDate(utcDate);
	}

	return {
		start : start
	};
})();

Schedule.Manager.Helpers = (function() {
	var monthNames = [ "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin",
			"Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" ];

	var dayNames = [ "Dimanche", "Lundi", "Mardi", "Merc", "Jeudi", "Vendredi",
			"Samedi" ];

	function convertDateToUTC(date) {
		return new Date(date.getUTCFullYear(), date.getUTCMonth(), date
				.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date
				.getUTCSeconds());
	}

	function addDays(d, days) {
		if (arguments.length < 2)
			days = 1;
		var t = new Date(d);
		t.setDate(t.getDate() + days);
		return t;
	}

	function setToLastSunday(d) {
		var t = new Date(d);
		t.setDate(t.getDate() - t.getDay());
		return t;
	}

	function getSmallDate(d) {
		return [ d.getMonth() + 1, "/", d.getDate() ].join("");
	}

	function getMonthName(index) {
		return monthNames[index];
	}

	function getDayName(index) {
		return dayNames[index];
	}

	function dateValid(d) {
		return isNaN(d.getMonth()) ? false : true;
	}

	function buildNewDateFromSelection(selectedDateValue, isEdgeMonthBefore,
			isEdgeMonthAfter) {
		log("arguments: ", arguments);

		var activeYear = Schedule.Manager.MonthViewUpdater.getActiveYear();
		console.log("activeMonth before: " + activeMonth);

		console.log("activeMonth: " + activeMonth);

		// console.log('selectedDateValue: ' + selectedDateValue);
		var date = new Date(activeYear, activeMonth,
				parseInt(selectedDateValue));
		console.log("date: ", convertDateToUTC(date));
		return convertDateToUTC(date);
	}

	function setActiveDate(utcDate, isEdgeBefore, isEdgeAfter) {

		if (arguments.length < 2)
			isEdgeBefore = false;
		if (arguments.length < 3)
			isEdgeAfter = false;

		Schedule.ActiveDate = utcDate;
		console.log("set to ", Schedule.ActiveDate);
		Schedule.HeaderControl.setActiveDate(utcDate);

		Schedule.Manager.DayViewUpdater.update(utcDate);
		Schedule.Manager.WeekViewUpdater.update(utcDate);
		Schedule.Manager.MonthViewUpdater.update(utcDate, isEdgeBefore,
				isEdgeAfter);
		// console.log('setActiveDate: ', utcDate);
		setDatePickerValue(utcDate);
	}

	function setDatePickerValue(utcDate) {

		var date = utcDate.getDate();

		var dateStr = [ utcDate.getFullYear(),
				month.toString().length > 1 ? month : [ "0", month ].join(""),
				date.toString().length > 1 ? date : [ "0", date ].join("") ]
				.join("-");

		// console.log('setDatePickerValue: ' + dateStr);

		$("#datepicker").val(dateStr);
	}

	return {
		convertDateToUTC : convertDateToUTC,
		addDays : addDays,
		setToLastSunday : setToLastSunday,
		getSmallDate : getSmallDate,
		getMonthName : getMonthName,
		getDayName : getDayName,
		dateValid : dateValid,
		buildNewDateFromSelection : buildNewDateFromSelection,
		setActiveDate : setActiveDate
	};
})();

Schedule.Manager.ViewSwitcher = (function() {
	var helpers = Schedule.Manager.Helpers;
	var $dayView, $weekView, $monthView, $dayBtn, $weekBtn, $dayLabel, $weekLabel, $monthLabel;

	function init() {
		$dayView = $("#day-view");
		$weekView = $("#week-view");

		$dayBtn = $("#view-switch-day");
		$weekBtn = $("#view-switch-week");

		$dayBtn.on("click", function() {
			Schedule.Navigation.Controller.addNavigationEvent("day",
					Schedule.ActiveDate);
			switchToDay();
		});
		$weekBtn.on("click", function() {
			Schedule.Navigation.Controller.addNavigationEvent("week",
					Schedule.ActiveDate);
			switchToWeek();
		});

		var $headerControl = $("#cal-header-control");
		$dayLabel = $headerControl.find(".day-display");
		$weekLabel = $headerControl.find(".week-display");
	}

	function switchToDay() {

		$weekLabel.hide();
		$dayLabel.show();
		$weekBtn.removeClass("active");
		$dayBtn.addClass("active");
		$weekView.hide();
		$dayView.show();
	}

	function switchToWeek() {
		$dayLabel.hide();
		$weekLabel.show();
		$dayBtn.removeClass("active");
		$weekBtn.addClass("active");
		$dayView.hide();
		$weekView.show();
	}

	function switchToMonth() {
		$dayLabel.hide();
		$weekLabel.hide();

		$dayBtn.removeClass("active");
		$weekBtn.removeClass("active");

		$dayView.hide();
		$weekView.hide();

	}

	function showClickedDay(event, isSunday) {
		event.stopPropagation();
		var $day = isSunday ? $(event.currentTarget).parent()
				: $(event.currentTarget);
		var selectedDate = parseInt($day.find("span").html());
		var isEdgeBefore = $day.hasClass("before");
		var isEdgeAfter = $day.hasClass("after");
		var utcDate = Schedule.Manager.Helpers.buildNewDateFromSelection(
				selectedDate, isEdgeBefore, isEdgeAfter);

		Schedule.Navigation.Controller.addNavigationEvent("day", utcDate);
		Schedule.Manager.Helpers.setActiveDate(utcDate);
		Schedule.Manager.ViewSwitcher.switchToDay();
	}

	function showClickedWeek(event) {
		event.stopPropagation();
		var $day = $(event.currentTarget).parent();
		var selectedDate = parseInt($day.find("span").html());
		var isEdgeBefore = $day.hasClass("before");
		var isEdgeAfter = $day.hasClass("after");
		var utcDate = Schedule.Manager.Helpers.buildNewDateFromSelection(
				selectedDate, isEdgeBefore
		// isEdgeAfter
		);

		Schedule.Navigation.Controller.addNavigationEvent("week", utcDate);
		Schedule.Manager.Helpers.setActiveDate(utcDate);
		Schedule.Manager.ViewSwitcher.switchToWeek();
	}

	return {
		init : init,
		switchToDay : switchToDay,
		switchToWeek : switchToWeek,
		showClickedDay : showClickedDay,
		showClickedWeek : showClickedWeek
	};
})();
var ancienneValeurs;
// view builders - builds the markup for the calendar views
Schedule.Manager.WeekViewBuilder = (function() {
	var _rows = 9;
	// var _rowTemplate = [
	// '<div class="row">',
	// '<div class="cell"></div>',
	// '<div class="cell"></div>',
	// '<div class="cell"></div>',
	// '<div class="cell"></div>',
	// '<div class="cell"></div>',
	// '<div class="cell"></div>',
	// '<div class="cell"></div>',
	// "</div>"
	// ].join("");
	var decallage = 8.30;
	var decallage1 = parseInt(decallage);
	function buildView() {
		var rowMarkup = [];
		for (var i = 0; i < _rows; i++) {
			var _rowTemplate = [
					'<div class="row">',
					'<div class="cell" name ="selecte" data-r="' + i + '">'
					+ (i + decallage)	+ '"></div>',
					'<div class="cell" name ="selecte" data-r="' + i + '">'
					+ (i + decallage)	+ '"></div>',
					'<div class="cell" name ="selecte" data-r="' + i + '">'
					+ (i + decallage)	+ '"></div>',
					'<div class="cell" name ="selecte" data-r="' + i + '">'
					+ (i + decallage)	+ '"></div>',
					'<div class="cell" name ="selecte" data-r="' + i + '">'
					+ (i + decallage)	+ '"></div>',
					'<div class="cell" name ="selecte" data-r="' + i + '">'
					+ (i + decallage)	+ '"></div>',
					'<div class="cell" name ="selecte" data-r="' + i + '">'
					+ (i + decallage)	+ '"></div>', "</div>" ].join("");
			rowMarkup.push(_rowTemplate);
		}
		$("#week-view").append(rowMarkup.join(""));

		$(".cell").click(
				function() {
					if ($(this).attr("class") == "f") {
					} else {
						$(this).toggleClass("active").attr("style", "");
						$(".leftContent").fadeIn();
						$(".leftContent .addButton").click(
								function(e) {
									e.preventDefault();
									(val = $("#pop").val()), (colorVal = $(
											".leftContent input[type=color]")
											.val());

									$("div.active").css({
										"background-color" : colorVal,
										"border-bottom" : "none"
									}).empty().append(val)
											.removeClass("active");
								});
					}
				});

		$(".leftContent .red").click(function(e) {
			e.preventDefault();
			console.log($(this));
			$("div.active").each(function() {
				$(this).html(decallage + parseInt($(this).attr("data-r")))
				// .removeClass()
				.css({
					"background-color" : "initial",
					"border-bottom" : "1px solid lightgrey"
				}).removeClass("active");
				;
			});

		});
	}

	return {
		buildView : buildView
	};
})();

Schedule.Manager.MonthViewBuilder = (function() {
	var _rows = 6;
	var _rowTemplate = [
			'<div class="row">',
			'<div class="cell">',
			'<div class="day">',
			'<div class="day-view-link half"  onclick="Schedule.Manager.ViewSwitcher.showClickedDay(arguments[0], true)">Day</div>',
			'<div class="week-view-link half"  onclick="Schedule.Manager.ViewSwitcher.showClickedWeek(arguments[0])">Week</div>',
			"<span></span>",
			"</div>",
			"</div>",
			'<div class="cell"><div class="day" onclick="Schedule.Manager.ViewSwitcher.showClickedDay(arguments[0])"><div class="day-view-link">Day</div><span></span></div></div>',
			'<div class="cell"><div class="day" onclick="Schedule.Manager.ViewSwitcher.showClickedDay(arguments[0])"><div class="day-view-link">Day</div><span></span></div></div>',
			'<div class="cell"><div class="day" onclick="Schedule.Manager.ViewSwitcher.showClickedDay(arguments[0])"><div class="day-view-link">Day</div><span></span></div></div>',
			'<div class="cell"><div class="day" onclick="Schedule.Manager.ViewSwitcher.showClickedDay(arguments[0])"><div class="day-view-link">Day</div><span></span></div></div>',
			'<div class="cell"><div class="day" onclick="Schedule.Manager.ViewSwitcher.showClickedDay(arguments[0])"><div class="day-view-link">Day</div><span></span></div></div>',
			'<div class="cell"><div class="day" onclick="Schedule.Manager.ViewSwitcher.showClickedDay(arguments[0])"><div class="day-view-link">Day</div><span></span></div></div>',
			"</div>" ].join("");

	function buildView() {
		var rowMarkup = [];
		for (var i = 0; i < _rows; i++) {
			rowMarkup.push(_rowTemplate);
		}
		$("#month-view").append(rowMarkup.join(""));
	}

	return {
		buildView : buildView
	};
})();

Schedule.Manager.DayViewBuilder = (function() {
	function buildView() {
	}

	return {
		buildView : buildView
	};
})();

// view updaters - updates the views and their labels to the given date
Schedule.Manager.WeekViewUpdater = (function() {
	var helpers = Schedule.Manager.Helpers;
	var $weekView, $sun, $mon, $tue, $wed, $thu, $fri, $sat;

	function init() {
		$weekView = $("#week-view");
		$sun = $weekView.find(".header.sun span");
		$mon = $weekView.find(".header.mon span");
		$tue = $weekView.find(".header.tue span");
		$wed = $weekView.find(".header.wed span");
		$thu = $weekView.find(".header.thu span");
		$fri = $weekView.find(".header.fri span");
		$sat = $weekView.find(".header.sat span");
	}

	function update(utcDate) {
		if (!helpers.dateValid(utcDate)) {
			// when an invalid date is provided, default to current date
			utcDate = helpers.convertDateToUTC(new Date());
		}

		var dayIndex = utcDate.getDay() + 1;
		$weekView.find(".cell.header").removeClass("selected");

		utcDate = helpers.setToLastSunday(utcDate);
		var start = [ helpers.getMonthName(utcDate.getMonth()), " ",
				utcDate.getDate() ].join("");

		var sun = helpers.getSmallDate(helpers.addDays(utcDate, +6));
		$sun.html(sun);
		if (dayIndex === 1)
			$sun.parent().addClass("selected");

		var mon = helpers.getSmallDate(helpers.addDays(utcDate, 1));
		$mon.html(mon);
		if (dayIndex === 2)
			$mon.parent().addClass("selected");

		var tue = helpers.getSmallDate(helpers.addDays(utcDate, 2));
		$tue.html(tue);
		if (dayIndex === 3)
			$tue.parent().addClass("selected");

		var wed = helpers.getSmallDate(helpers.addDays(utcDate, 3));
		$wed.html(wed);
		if (dayIndex === 4)
			$wed.parent().addClass("selected");

		var thu = helpers.getSmallDate(helpers.addDays(utcDate, 4));
		$thu.html(thu);
		if (dayIndex === 5)
			$thu.parent().addClass("selected");

		var fri = helpers.getSmallDate(helpers.addDays(utcDate, 5));
		$fri.html(fri);
		if (dayIndex === 6)
			$fri.parent().addClass("selected");

		var satDate = helpers.getSmallDate(helpers.addDays(utcDate, 6));
		var sat = helpers.getSmallDate(helpers.addDays(utcDate, 0));
		;
		$sat.html(sat);
		if (dayIndex === 7)
			$sat.parent().addClass("selected");

		var end = [ helpers.getMonthName(satDate.getMonth()), " ",
				satDate.getDate() ].join("");

		var labelDisplay = [ start, " - ", end ].join("");
		Schedule.HeaderControl.setDisplayText(".week-display", labelDisplay);
	}

	return {
		init : init,
		update : update
	};
})();

Schedule.Manager.MonthViewUpdater = (function() {
	var helpers = Schedule.Manager.Helpers;

	var activeDate;

	function init() {
		$monthView = $("#month-view");
	}

	function update(utcDate, isEdgeBefore, isEdgeAfter) {
		if (!helpers.dateValid(utcDate)) {
			// when an invalid date is provided, default to current date
			utcDate = helpers.convertDateToUTC(new Date());
		}

		var performTransition = isEdgeBefore || isEdgeAfter;

		if (performTransition) {
			// new month render
			transitionOut();
			setTimeout(function() {
				updateCells(utcDate);
				transitionIn();
			}, 1000);
		} else {
			// same month render
			updateCells(utcDate);
		}
	}

	function updateCells(utcDate) {
		activeDate = utcDate;
		var monthDate = new Date(utcDate);
		var originalDate = utcDate;
		var selectedDate = originalDate.getDate();

		monthDate.setDate(1);
		var labelDisplay = [ helpers.getMonthName(monthDate.getMonth()),
				monthDate.getFullYear() ].join(" ");
		Schedule.HeaderControl.setDisplayText(".month-display", labelDisplay);
		var $cells = $monthView.find(".cell:not(.header)");
		$cells.removeClass("selected");
		$cells.find(".day").removeClass("edge before after");
		var dayIndex = monthDate.getDay();
		var prevDate = monthDate;

		for (var j = 0; j < dayIndex; j++) {
			var index = dayIndex - (j + 1);
			prevDate = helpers.addDays(monthDate, -(j + 1));
			var $day = $($cells[index]).find(".day");
			if (prevDate.getDate() === selectedDate
					&& prevDate.getMonth() === selectedMonth)
				$day.parent().addClass("selected");

			$day.addClass("edge before");

			$day.parent().find("span").html(prevDate.getDate());
		}

		var prevDate = -1;

		var isEdgeDate = false;
		for (var i = dayIndex; i < $cells.length; i++) {
			if (!isEdgeDate) {
				isEdgeDate = prevDate > monthDate.getDate();
			}

			prevDate = monthDate.getDate();
			var $day = $($cells[i]).find(".day");

			if (monthDate.getDate() === selectedDate
					&& monthDate.getMonth() === selectedMonth)
				$day.parent().addClass("selected");
			if (isEdgeDate)
				$day.addClass("edge after");

			$day.find("span").html(prevDate);
			monthDate = helpers.addDays(monthDate, 1);
		}
	}

	function transitionOut() {
		$monthView.fadeOut();
	}

	function transitionIn() {
		$monthView.fadeIn();
	}

	function getActiveMonth() {

	}

	function getActiveYear() {
		return activeDate.getFullYear();
	}

	return {
		init : init,
		update : update,
		getActiveMonth : getActiveMonth,
		getActiveYear : getActiveYear
	};
})();

Schedule.Manager.DayViewUpdater = (function() {
	var helpers = Schedule.Manager.Helpers;
	var $dayView;

	function init() {
		$dayView = $("#day-view");
	}

	function update(utcDate) {
		var date = utcDate.getDate();
		var monthName = helpers.getMonthName(utcDate.getMonth());
		var dayName = helpers.getDayName(utcDate.getDay());
		var year = utcDate.getFullYear();
		$dayView.find(".cell span").html(date);

		var labelDisplay = [ dayName, ", ", monthName, " ", date, " ", year ]
				.join("");
		Schedule.HeaderControl.setDisplayText(".day-display", labelDisplay);
	}

	return {
		init : init,
		update : update
	};
})();

Schedule.EventManager = (function() {
	function registerExistingEvent(event) {
	}

	function registerNewEvent(event) {
	}

	function getEventDate(eventId) {
	}

	return {
		registerExistingEvent : registerExistingEvent,
		registerNewEvent : registerNewEvent,
		getEventDate : getEventDate
	};
})();

Schedule.HeaderControl = (function() {
	var helpers = Schedule.Manager.Helpers;
	var $control, $display, $monthsContainer, activeDate, isOpen = false;

	function init() {
		$control = $("#cal-header-control");
		$display = $control.find("#cal-header-control-display");
		$monthsContainer = $control.find(".months-container");

		$control.on("click", onControlClick);
		$monthsContainer.find(".month-btn").on("click", onMonthClick);

		var $monthDisplay = $display.find(".month-display");

		$monthDisplay.find(".prev-month").on("click", function(e) {
			e.stopPropagation();
			Schedule.QuickNavigation.goToPrevMonth(activeDate);
		});

		$monthDisplay.find(".next-month").on("click", function(e) {
			e.stopPropagation();
			Schedule.QuickNavigation.goToNextMonth(activeDate);
		});
	}

	function onControlClick() {
		$monthsContainer.toggleClass("hide");
	}

	function onMonthClick(e) {
		e.stopPropagation();
		var selectedMonth = $(this).data("index") - 1;
		var activeYear = activeDate.getFullYear();

		var date = new Date(activeYear, selectedMonth, 1);
		var utcDate = helpers.convertDateToUTC(date);
		Schedule.Manager.DayViewUpdater.update(utcDate);
		Schedule.Manager.WeekViewUpdater.update(utcDate);
		Schedule.Manager.MonthViewUpdater.update(utcDate);
		Schedule.Manager.ViewSwitcher.switchToMonth();

		onControlClick();
	}

	function setActiveDate(newActiveDate) {
		activeDate = newActiveDate;
	}

	function setDisplayText(classSelector, newDisplayText) {
		console.log("setDisplayText: " + newDisplayText);
		var selector = [ classSelector, " .date" ].join("");
		$display.find(selector).html(newDisplayText);
	}

	return {
		init : init,
		setActiveDate : setActiveDate,
		setDisplayText : setDisplayText
	};
})();

Schedule.QuickNavigation = (function() {
	function goToJanuary(activeDate) {
		var newDate = activeDate;
		newDate.setMonth(0);
		Schedule.Manager.Helpers.setActiveDate(newDate);
	}

	function goToDecember(activeDate) {
		var newDate = activeDate;
		newDate.setMonth(11);
		Schedule.Manager.Helpers.setActiveDate(newDate);
	}

	function goToPrevMonth(activeDate) {
		var newDate = activeDate;
		var isJan = newDate.getMonth() === 0;
		if (isJan) {
			var year = newDate.getFullYear();
			var newYear = parseInt(year) - 1;
			newDate.setFullYear(newYear);
			newDate.setMonth(11);
		} else {
			newDate.setDate(1);
			var month = newDate.getMonth();
			console.log("month: " + month);
			var newMonth = parseInt(month) - 1;
			console.log("new month: " + newMonth);
			newDate.setMonth(newMonth);
		}
		console.log("newDate: " + newDate.toString());
		Schedule.Manager.Helpers.setActiveDate(newDate);
	}

	function goToNextMonth(activeDate) {
		var newDate = activeDate;
		var isDec = newDate.getMonth() === 11;
		if (isDec) {
			var year = newDate.getFullYear();
			var newYear = parseInt(year) + 1;
			newDate.setFullYear(newYear);
			newDate.setMonth(0);
		} else {
			newDate.setDate(1);
			var month = newDate.getMonth();
			var newMonth = parseInt(month) + 1;
			newDate.setMonth(newMonth);
		}
		// console.log('newDate: ' + newDate.toString());
		Schedule.Manager.Helpers.setActiveDate(newDate);
	}

	return {
		goToJanuary : goToJanuary,
		goToDecember : goToDecember,
		goToPrevMonth : goToPrevMonth,
		goToNextMonth : goToNextMonth
	};
})();

$(document).ready(Schedule.Manager.Controller.start);
