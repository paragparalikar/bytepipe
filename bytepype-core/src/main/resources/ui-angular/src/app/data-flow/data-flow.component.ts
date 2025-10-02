import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { NgFor } from '@angular/common';
import { DataflowEditorComponent } from './editor/dataflow-editor.component';
import { DataFlowService } from './data-flow.service';
import { Dataflow } from './data-flow.model';
import { CreatedByComponent } from "../user/created-by/created-by.component";

@Component({
  selector: 'app-data-flow',
  imports: [NgFor, DataflowEditorComponent, CreatedByComponent],
  templateUrl: './data-flow.component.html',
  styleUrl: './data-flow.component.css'
})
export class DataFlowComponent implements OnInit, AfterViewInit {

  dataflows: Dataflow[] = [];
  @ViewChild('dataflowEditor') dataflowEditor!: DataflowEditorComponent;

  constructor(private dataFlowService: DataFlowService) {}

  ngOnInit(): void {
    this.refresh();
  }

  ngAfterViewInit(): void {
    this.dataflowEditor.isOpen.subscribe(value => {
      if(!value) this.refresh();
    });
  }

  private refresh() {
    this.dataFlowService.findAll().subscribe(dataflows => {
      this.dataflows = dataflows;
    });
  }

  onCreateDataFlow() {
    this.dataflowEditor.show('0');
  }

  onEdit(dataflow: Dataflow) {
    this.dataflowEditor.show(dataflow.id!);
  }

  onDelete(dataflow: Dataflow) {
    if (confirm(`Are you sure you want to delete dataflow "${dataflow.name}"?`)) {
      this.dataFlowService.delete(dataflow.id!).subscribe(() => {
        this.refresh();
      });
    }
  }

  onToggleEnabled(dataflow: Dataflow) {
    const updatedDataflow = new Dataflow({
      ...dataflow,
      enabled: !dataflow.enabled
    });
    
    this.dataFlowService.update(dataflow.id!, updatedDataflow).subscribe(() => {
      this.refresh();
    });
  }

}
