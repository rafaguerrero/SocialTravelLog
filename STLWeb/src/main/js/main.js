/**
 * Created by rafaelguerreroclemente on 15/7/17.
 */

import ArticleCreation from 'components/ArticleCreation';

function main() {
    let articleCreation = document.querySelector(".articleCreation");
    !!articleCreation && (new ArticleCreation(articleCreation));
}

main();