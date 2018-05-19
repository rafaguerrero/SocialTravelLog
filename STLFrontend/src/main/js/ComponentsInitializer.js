
import * as components from 'components';

export default {
    initilize() {
        document.querySelectorAll("[data-component-name]").forEach((element) => {
            let name = element.getAttribute("data-component-name"),
                componentConstructor = components[name];

            if(!componentConstructor) {
                throw new Error("Component " + name + " doesn't exist");
            }

            element[name] = new componentConstructor(element);
        });
    }
};