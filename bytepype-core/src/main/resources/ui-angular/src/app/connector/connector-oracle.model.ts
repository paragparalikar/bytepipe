import { Connector } from "./connector.model";

export class OracleConnector extends Connector {

    url?: string|null;
    username?: string|null;
    password?: string|null;

}