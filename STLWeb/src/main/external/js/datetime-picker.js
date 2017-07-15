// ********************************************
// Version 1.0.0
// ********************************************
angular.module('ui.bootstrap.datetimepicker', ['ui.bootstrap.dateparser', 'ui.bootstrap.position'])
	.directive('datetimePicker', ['$compile', '$parse', '$document', '$position', 'dateFilter', 'dateParser', 'datepickerPopupConfig',
		function ($compile, $parse, $document, $position, dateFilter, dateParser, datepickerPopupConfig) {
			return {
				restrict: 'A',
				require: 'ngModel',
				scope: {
					isOpen: '=?',
					enableDate: '=?',
					enableTime: '=?',
					todayText: '@',
					nowText: '@',
					dateText: '@',
					timeText: '@',
					clearText: '@',
					closeText: '@',
					dateDisabled: '&'
				},
				link: function (scope, element, attrs, ngModel) {
					var dateFormat,
						closeOnDateSelection = angular.isDefined(attrs.closeOnDateSelection) ? scope.$parent.$eval(attrs.closeOnDateSelection) : datepickerPopupConfig.closeOnDateSelection,
						appendToBody = angular.isDefined(attrs.datepickerAppendToBody) ? scope.$parent.$eval(attrs.datepickerAppendToBody) : datepickerPopupConfig.appendToBody;

					scope.showButtonBar = angular.isDefined(attrs.showButtonBar) ? scope.$parent.$eval(attrs.showButtonBar) : datepickerPopupConfig.showButtonBar;

					// determine which pickers should be available. Defaults to date and time
					scope.enableDate = !(scope.enableDate == false);
					scope.enableTime = !(scope.enableTime == false);

					// default picker view
					scope.showPicker = scope.enableDate ? 'date' : 'time';

					// if date and time picker is available, set time picker to particular width to match datepicker
					scope.timeStyle = scope.enableDate && scope.enableTime ? { 'min-width': '268px' } : { 'min-width' : '160px' };

					// default text
					scope.todayText = scope.todayText || 'Today';
					scope.nowText = scope.nowText || 'Now';
					scope.clearText = scope.clearText || 'Clear';
					scope.closeText = scope.closeText || 'Close';
					scope.dateText = scope.dateText || 'Date';
					scope.timeText = scope.timeText || 'Time';

					scope.getText = function (key) {
						return scope[key + 'Text'] || datepickerPopupConfig[key + 'Text'];
					};

					attrs.$observe('datetimePicker', function (value) {
						dateFormat = value || datepickerPopupConfig.datepickerPopup;
						ngModel.$render();
					});

					// popup element used to display calendar
					var popupEl = angular.element('' +
					'<div datetime-picker-popup>' +
					'<div collapse="showPicker == \'time\'">' +
					'<div datepicker></div>' +
					'</div>' +
					'<div collapse="showPicker == \'date\'" ng-style="timeStyle">' +
					'<div timepicker style="margin:0 auto"></div>' +
					'</div>' +
					'</div>');

					// get attributes from directive
					popupEl.attr({
						'ng-model': 'date',
						'ng-change': 'dateSelection()'
					});

					function cameltoDash(string) {
						return string.replace(/([A-Z])/g, function ($1) { return '-' + $1.toLowerCase(); });
					}

					// datepicker element
					var datepickerEl = angular.element(popupEl.children()[0].children[0]);

					if (attrs.datepickerOptions) {
						angular.forEach(scope.$parent.$eval(attrs.datepickerOptions), function (value, option) {
							datepickerEl.attr(cameltoDash(option), value);
						});
					}

					// timepicker element
					var timepickerEl = angular.element(popupEl.children()[1].children[0]);
					if (attrs.timepickerOptions) {
						angular.forEach(scope.$parent.$eval(attrs.timepickerOptions), function (value, option) {
							timepickerEl.attr(cameltoDash(option), value);
						});
					}

					scope.watchData = {};

					angular.forEach([ 'maxDate', 'datepickerMode'], function (key) {

						if (attrs[key]) {
							var getAttribute = $parse(attrs[key]);
							scope.$parent.$watch(getAttribute, function (value) {
								scope.watchData[key] = value;
							});
							datepickerEl.attr(cameltoDash(key), 'watchData.' + key);

							// Propagate changes from datepicker to outside
							if (key === 'datepickerMode') {
								var setAttribute = getAttribute.assign;
								scope.$watch('watchData.' + key, function (value, oldvalue) {
									if (value !== oldvalue) {
										setAttribute(scope.$parent, value);
									}
								});
							}
						}
					});

					if (attrs.dateDisabled) {
						datepickerEl.attr('date-disabled', 'dateDisabled({ date: date, mode: mode })');
					}

					function parseDate(viewValue) {
						if (!viewValue) {
							ngModel.$setValidity('date', true);
							return null;
						} else if (angular.isDate(viewValue) && !isNaN(viewValue)) {
							ngModel.$setValidity('date', true);
							return viewValue;
						} else if (angular.isString(viewValue)) {
							var date = dateParser.parse(viewValue, dateFormat) || new Date(viewValue);
							if (isNaN(date)) {
								ngModel.$setValidity('date', false);
								return undefined;
							} else {
								ngModel.$setValidity('date', true);
								return date;
							}
						} else {
							ngModel.$setValidity('date', false);
							return undefined;
						}
					}
					ngModel.$parsers.unshift(parseDate);

					// Inner change
					scope.dateSelection = function (dt) {
						if (angular.isDefined(dt)) {
							scope.date = dt;
						}
						ngModel.$setViewValue(scope.date);
						ngModel.$render();

						if (closeOnDateSelection && scope.showPicker != 'time') {
							scope.isOpen = false;
							element[0].focus();
						}
					};

					element.bind('input change keyup', function () {
						scope.$apply(function () {
							scope.date = ngModel.$modelValue;
						});
					});

					// Outter change
					ngModel.$render = function () {
						var date = ngModel.$viewValue ? dateFilter(ngModel.$viewValue, dateFormat) : '';
						element.val(date);
						scope.date = parseDate(ngModel.$modelValue);
					};

					var documentClickBind = function (event) {
						if (scope.isOpen && event.target !== element[0]) {
							scope.$apply(function () {
								scope.isOpen = false;
							});
						}
					};

					var keydown = function (evt, noApply) {
						scope.keydown(evt);
					};
					element.bind('keydown', keydown);

					scope.keydown = function (evt) {
						if (evt.which === 27) {
							evt.preventDefault();
							evt.stopPropagation();
							scope.close();
						} else if (evt.which === 40 && !scope.isOpen) {
							scope.isOpen = true;
						}
					};

					scope.$watch('isOpen', function (value) {
						if (value) {
							scope.$broadcast('datepicker.focus');
							scope.position = appendToBody ? $position.offset(element) : $position.position(element);
							scope.position.top = scope.position.top + element.prop('offsetHeight');

							$document.bind('click', documentClickBind);
						} else {
							$document.unbind('click', documentClickBind);
						}
					});

					scope.select = function (date) {
						if (date === 'today') {
							var today = new Date();
							if (angular.isDate(ngModel.$modelValue)) {
								date = new Date(ngModel.$modelValue);
								date.setFullYear(today.getFullYear(), today.getMonth(), today.getDate());
							} else {
								date = new Date(today.setHours(0, 0, 0, 0));
							}
						} else if (date === 'now') {
							if (angular.isDate(ngModel.$modelValue)) {
								var now = new Date();
								date = new Date(ngModel.$modelValue);
								date.setHours(now.getHours(), now.getMinutes(), now.getSeconds(), now.getMilliseconds());
							} else {
								date = new Date();
							}
						}
						scope.dateSelection(date);
					};

					scope.close = function () {
						scope.isOpen = false;
						element[0].focus();
					};

					scope.changePicker = function (e) {
						scope.showPicker = e;
					};

					var $popup = $compile(popupEl)(scope);
					// Prevent jQuery cache memory leak (template is now redundant after linking)
					popupEl.remove();

					if (appendToBody) {
						$document.find('body').append($popup);
					} else {
						element.after($popup);
					}

					scope.$on('$destroy', function () {
						$popup.remove();
						element.unbind('keydown', keydown);
						$document.unbind('click', documentClickBind);
					});
				}
			};
		}])

	.directive('datetimePickerPopup', function () {
		return {
			restrict: 'EA',
			replace: true,
			transclude: true,
			templateUrl: 'template/datetime-picker.html',
			link: function (scope, element, attrs) {
				element.bind('click', function (event) {
					event.preventDefault();
					event.stopPropagation();
				});
			}
		};
	});

angular.module('ui.bootstrap.datetimepicker').run(['$templateCache', function($templateCache) {
	'use strict';

	$templateCache.put('template/datetime-picker.html',
		"<ul class=\"dropdown-menu dropdown-menu-right\" ng-style=\"{display: (isOpen && 'block') || 'none', top: position.top+'px', right: position.right+'px'}\" ng-keydown=keydown($event)><li style=\"padding:0 5px 5px 5px\" class=datetime-picker><div ng-transclude></div></li><li ng-if=showButtonBar style=padding:5px><span class=\"btn-group pull-left\"><button ng-if=\"showPicker == 'date'\" type=button class=\"btn btn-sm btn-info\" ng-click=\"select('today')\">{{ getText('today') }}</button> <button ng-if=\"showPicker == 'time'\" type=button class=\"btn btn-sm btn-info\" ng-click=\"select('now')\">{{ getText('now') }}</button> <button type=button class=\"btn btn-sm btn-danger\" ng-click=select(null)>{{ getText('clear') }}</button></span> <span class=\"btn-group pull-right\"><button ng-if=\"showPicker == 'date' && enableTime\" type=button class=\"btn btn-sm btn-default\" ng-click=\"changePicker('time')\">{{ getText('time')}}</button> <button ng-if=\"showPicker == 'time' && enableDate\" type=button class=\"btn btn-sm btn-default\" ng-click=\"changePicker('date')\">{{ getText('date')}}</button> <button type=button class=\"btn btn-sm btn-success\" ng-click=close()>{{ getText('close') }}</button></span></li></ul>"
	);

}]);