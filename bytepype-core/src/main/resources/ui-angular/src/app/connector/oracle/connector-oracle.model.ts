import { Connector } from "../connector.model";

export class OracleConnector extends Connector {

    constructor(value?: Partial<OracleConnector>){
        super(value);
        Object.assign(this, value);
    }

    url?: string|null;
    username?: string|null;
    password?: string|null;

}