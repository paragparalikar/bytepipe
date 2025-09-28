import { ConnectorType } from "./connector-type.enum";

export class Connector {

    constructor(value?: Partial<Connector>){
        Object.assign(this, value);
    }
    
    id?: number|null;
    name?: string|null;
    description?: string|null;
    type?: string|null;
    createdBy?: string|null;
    createdDate?: Date|null;
    lastUpdatedBy?: string|null;
    lastUpdatedDate?: string|null;

}