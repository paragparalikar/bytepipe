export class Dataflow {

    constructor(value?: Partial<Dataflow>){
        Object.assign(this, value);
    }

    id?: string|null;
    name?: string|null;
    enabled?: boolean|null;
    description?: string|null;
    sourceConnectorId?: number|null;
    destinationConnectorId?: number|null;
    createdBy?: string|null;
    createdDate?: Date|null;
    lastUpdatedBy?: string|null;
    lastUpdatedDate?: Date|null;
}
