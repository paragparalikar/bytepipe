import { ConnectorType } from "./connector-type.enum";

export class Connector {
    
    id?: number|null;
    name?: string|null;
    description?: string|null;
    type?: ConnectorType|null;
    createdBy?: string|null;
    createdDate?: Date|null;
    lastUpdatedBy?: string|null;
    lastUpdatedDate?: string|null;

}