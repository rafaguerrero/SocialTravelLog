/**
 * Created by rafaelguerreroclemente on 15/7/17.
 */

import FormPost from 'components/FormPost';

function main() {
    let formPost = document.querySelector(".stl-form-post");
    !!formPost && (new FormPost(formPost));
}

main();