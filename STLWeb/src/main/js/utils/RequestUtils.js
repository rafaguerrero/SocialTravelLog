/**
 * Created by rafaelguerreroclemente on 23/8/17.
 */

module.exports = {
    post : function(url, data, successCallback, errorCallback) {
        var request = new XMLHttpRequest();
        request.timeout = 10000;
        request.open("POST", url);
        request.setRequestHeader("Content-Type", "application/json");

        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                const responseHeaders = request.getAllResponseHeaders();

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
}