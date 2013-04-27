package com.pushpop.client.view;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ProvidesKey;
import com.pushpop.client.proxy.QuestionProxy;

public class QuestionsCellList extends CellList<QuestionProxy> {

    public QuestionsCellList(Cell<QuestionProxy> cell, ProvidesKey<QuestionProxy> keyProvider) {
        super(cell, keyProvider);
        // TODO Auto-generated constructor stub
    }

    public QuestionsCellList(Cell<QuestionProxy> cell, com.google.gwt.user.cellview.client.CellList.Resources resources, ProvidesKey<QuestionProxy> keyProvider) {
        super(cell, resources, keyProvider);
        // TODO Auto-generated constructor stub
    }

    public QuestionsCellList(Cell<QuestionProxy> cell, com.google.gwt.user.cellview.client.CellList.Resources resources) {
        super(cell, resources);
        // TODO Auto-generated constructor stub
    }

    public QuestionsCellList(Cell<QuestionProxy> cell) {
        super(cell);
        // TODO Auto-generated constructor stub
    }
}
