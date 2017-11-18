/**
 * Created by rafaelguerreroclemente on 23/8/17.
 */

import RequestUtils from 'utils/RequestUtils';

const getDataFromForm = (form) => {
    var formData = new FormData(form),
        result = {};

    for (var entry of formData.entries()) {
        result[entry[0]] = entry[1];
    }

    return JSON.stringify(result);
};

const getApiLocation = () => {
    let location = window.location.href;

    if(location.indexOf(".html") >= 0) {
        return location.replace(".html", ".json");
    }

    return location + ".json";
}

export default class FormPost {
    constructor(form) {
        this.form = form;
        this.form.addEventListener("submit", this.submit.bind(this));
    }

    submit(event) {
        event.preventDefault();

        RequestUtils.post(getApiLocation(), getDataFromForm(this.form))
                    .then(
                        () => { alert("Creation Successfull") }
                    ).catch(
                        () => { alert("There was an error") }
                    )
    }
}
