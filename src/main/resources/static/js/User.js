export default class User {
    constructor({id, firstname, lastname, age, username, rolesAsString}) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.roles = rolesAsString;
    }
};
