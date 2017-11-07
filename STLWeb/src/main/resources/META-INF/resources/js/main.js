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

	var _componentsArticleCreation = __webpack_require__(1);

	var _componentsArticleCreation2 = _interopRequireDefault(_componentsArticleCreation);

	function main() {
	  var articleCreation = document.querySelector(".articleCreation");
	  !!articleCreation && new _componentsArticleCreation2["default"](articleCreation);
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

	var CREATION_URL = "/hub/article/save";

	var getDataFromForm = function getDataFromForm(form) {
	    var title = form.querySelector(".articleCreation__form__title"),
	        author = form.querySelector(".articleCreation__form__author"),
	        body = form.querySelector(".articleCreation__form__body");

	    var data = {
	        "title": title.value,
	        "body": body.value,
	        "author": { "userId": author.value }
	    };

	    return JSON.stringify(data);
	};

	var ArticleCreation = (function () {
	    function ArticleCreation(page) {
	        _classCallCheck(this, ArticleCreation);

	        this.page = page;
	        this.main = page.querySelector(".articleCreation__main");
	        this.ok = page.querySelector(".articleCreation__ok");
	        this.error = page.querySelector(".articleCreation__error");

	        this.form = page.querySelector(".articleCreation__form");
	        this.form.addEventListener("submit", this.create.bind(this));
	    }

	    //module.exports = ArticleCreationForm;

	    _createClass(ArticleCreation, [{
	        key: "create",
	        value: function create(event) {
	            var _this = this;

	            event.preventDefault();

	            this.page.classList.add("loading");
	            this.main.classList.add("hide");

	            _utilsRequestUtils2["default"].post(CREATION_URL, getDataFromForm(this.form), function () {
	                _this.page.classList.remove("loading");
	                _this.ok.classList.remove("hide");
	            }, function () {
	                _this.page.classList.remove("loading");
	                _this.error.classList.remove("hide");
	            });
	        }
	    }]);

	    return ArticleCreation;
	})();

	exports["default"] = ArticleCreation;
	module.exports = exports["default"];

/***/ }),
/* 2 */
/***/ (function(module, exports) {

	/**
	 * Created by rafaelguerreroclemente on 23/8/17.
	 */

	"use strict";

	module.exports = {
	    post: function post(url, data, successCallback, errorCallback) {
	        var request = new XMLHttpRequest();
	        request.timeout = 10000;
	        request.open("POST", url);
	        request.setRequestHeader("Content-Type", "application/json");

	        request.onreadystatechange = function () {
	            if (request.readyState === 4) {
	                var responseHeaders = request.getAllResponseHeaders();

	                if (request.status >= 200 && request.status < 300) {
	                    successCallback && successCallback(request.responseText, responseHeaders);
	                } else {
	                    errorCallback && errorCallback(request, responseHeaders);
	                }
	            }
	        };

	        request.send(data);
	        return request;
	    }
	};

/***/ })
/******/ ]);