export default class Btn {
    constructor({text, className, userId}) {
        this.textContent = text;
        this.className = className;
        this.dataset = {
            userId: userId,
            btn: text,
        };
    }
}