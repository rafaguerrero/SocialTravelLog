/**
 * Created by rafaelguerreroclemente on 23/8/17.
 */

import RequestUtils from 'utils/RequestUtils';

const CREATION_URL = "/hub/article/save"

const getDataFromForm = (form) => {
    let title = form.querySelector(".articleCreation__form__title"),
        author = form.querySelector(".articleCreation__form__author"),
        body = form.querySelector(".articleCreation__form__body");

    let data = {
        "title" : title.value,
        "body" : body.value,
        "author" : { "userId" : author.value }
    };

    return JSON.stringify(data);
}

export default class ArticleCreation {
    constructor(page) {
        this.page = page;
        this.main = page.querySelector(".articleCreation__main");
        this.ok = page.querySelector(".articleCreation__ok");
        this.error = page.querySelector(".articleCreation__error");

        this.form = page.querySelector(".articleCreation__form");
        this.form.addEventListener("submit", this.create.bind(this));
    }

    create(event) {
        event.preventDefault();

        this.page.classList.add("loading");
        this.main.classList.add("hide");

        RequestUtils.post(CREATION_URL,
                            getDataFromForm(this.form),
                            () => {
                                this.page.classList.remove("loading");
                                this.ok.classList.remove("hide");
                            },
                            () => {
                                this.page.classList.remove("loading");
                                this.error.classList.remove("hide");
                            })
    }
}

//module.exports = ArticleCreationForm;
