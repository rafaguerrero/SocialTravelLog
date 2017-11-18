/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

	/**
	 * Created by rafaelguerreroclemente on 15/7/17.
	 */

	"use strict";

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

	var _componentsFormPost = __webpack_require__(1);

	var _componentsFormPost2 = _interopRequireDefault(_componentsFormPost);

	function main() {
	  var formPost = document.querySelector(".stl-form-post");
	  !!formPost && new _componentsFormPost2["default"](formPost);
	}

	main();

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

	/**
	 * Created by rafaelguerreroclemente on 23/8/17.
	 */

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = (function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; })();

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var _utilsRequestUtils = __webpack_require__(2);

	var _utilsRequestUtils2 = _interopRequireDefault(_utilsRequestUtils);

	var getDataFromForm = function getDataFromForm(form) {
	    var formData = new FormData(form),
	        result = {};

	    var _iteratorNormalCompletion = true;
	    var _didIteratorError = false;
	    var _iteratorError = undefined;

	    try {
	        for (var _iterator = formData.entries()[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
	            var entry = _step.value;

	            result[entry[0]] = entry[1];
	        }
	    } catch (err) {
	        _didIteratorError = true;
	        _iteratorError = err;
	    } finally {
	        try {
	            if (!_iteratorNormalCompletion && _iterator["return"]) {
	                _iterator["return"]();
	            }
	        } finally {
	            if (_didIteratorError) {
	                throw _iteratorError;
	            }
	        }
	    }

	    return JSON.stringify(result);
	};

	var getApiLocation = function getApiLocation() {
	    var location = window.location.href;

	    if (location.indexOf(".html") >= 0) {
	        return location.replace(".html", ".json");
	    }

	    return location + ".json";
	};

	var FormPost = (function () {
	    function FormPost(form) {
	        _classCallCheck(this, FormPost);

	        this.form = form;
	        this.form.addEventListener("submit", this.submit.bind(this));
	    }

	    _createClass(FormPost, [{
	        key: "submit",
	        value: function submit(event) {
	            event.preventDefault();

	            _utilsRequestUtils2["default"].post(getApiLocation(), getDataFromForm(this.form)).then(function () {
	                alert("Creation Successfull");
	            })["catch"](function () {
	                alert("There was an error");
	            });
	        }
	    }]);

	    return FormPost;
	})();

	exports["default"] = FormPost;
	module.exports = exports["default"];

/***/ }),
/* 2 */
/***/ (function(module, exports) {

	/**
	 * Created by rafaelguerreroclemente on 23/8/17.
	 */

	"use strict";

	module.exports = {
	    post: function post(url, data) {
	        return new Promise(function (resolve, reject) {
	            var request = new XMLHttpRequest();
	            request.timeout = 10000;
	            request.open("POST", url);
	            request.setRequestHeader("Content-Type", "application/json");

	            request.onreadystatechange = function () {
	                if (request.readyState === 4) {
	                    var responseHeaders = request.getAllResponseHeaders();

	                    if (request.status >= 200 && request.status < 300) {
	                        resolve(request.responseText, responseHeaders);
	                    } else {
	                        reject(request, responseHeaders);
	                    }
	                }
	            };

	            request.send(data);
	        });
	    }
	};

/***/ })
/******/ ]);