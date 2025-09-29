export class User {

    constructor(value: Partial<User>){
        Object.assign(this, value);
    }

    id?: string|null;
    name?: string|null;
    givenName?: string|null;
    familyName?: string|null;
    picture?: string|null;

}