/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.Bluefix.Prodosia.GUI.Taglist;

import com.Bluefix.Prodosia.DataType.Taglist;
import com.Bluefix.Prodosia.GUI.Helpers.DataFieldStorage;
import com.Bluefix.Prodosia.GUI.Helpers.EditableWindowPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EditTaglistWindow extends EditableWindowPane
{
    //region Constructor



    //endregion

    //region Textfields and labels


    @FXML public Label lbl_navigation;
    @FXML public Label lbl_deleteConfirmation;
    @FXML public Button button_confirmDelete;

    //endregion

    //region Button actions

    @FXML public Button button_back;
    @FXML public Button button_edit;
    @FXML public Button button_delete;



    public void btn_CancelBack(ActionEvent actionEvent)
    {
        super.button_Cancel_Back();
    }


    /**
     * Button that, depending on the current state, either states Edit or Save.
     *
     * The Edit button will store the fields that currently exist to allow for restoration.
     * The Save button will store the newly edited data.
     * @param actionEvent The action-event corresponding to the button press.
     */
    public void btn_EditSave(ActionEvent actionEvent)
    {
        super.button_Edit_Save();
    }

    /**
     * Button that, depending
     * @param actionEvent
     */
    public void btn_delete(ActionEvent actionEvent)
    {
        super.button_Delete();
    }

    public void btn_confirmDelete(ActionEvent actionEvent)
    {
        super.button_ConfirmDelete();
    }


    //endregion

    //region State handling

    @Override
    protected void applyState(EditableWindowPane.WindowState state)
    {
        switch (state)
        {

            case VIEW:
                lbl_navigation.setText("Inspect Taglist");

                lbl_deleteConfirmation.setVisible(false);
                button_confirmDelete.setVisible(false);
                button_delete.setDisable(false);
                button_delete.setText("Delete");
                button_back.setText("Back");
                button_edit.setText("Edit");
                button_back.setDisable(false);
                button_edit.setDisable(false);
                break;

            case EDIT:
                lbl_navigation.setText("Edit Taglist");

                lbl_deleteConfirmation.setVisible(false);
                button_confirmDelete.setVisible(false);
                button_delete.setDisable(true);
                button_back.setText("Cancel");
                button_edit.setText("Save");
                button_back.setDisable(false);
                button_edit.setDisable(false);
                break;

            case DELETE:
                lbl_deleteConfirmation.setVisible(true);
                button_confirmDelete.setVisible(true);
                button_delete.setText("No");

                button_back.setDisable(true);
                button_edit.setDisable(true);
                break;
        }
    }

    //endregion

    //region initialization

    private Taglist curTaglist;

    private void clearData()
    {

    }

    /**
     * Initialize an empty window
     */
    public void initialize()
    {
        curTaglist = null;

        clearData();

        setState(WindowState.EDIT);
    }

    /**
     * Initialize a window with the taglist information.
     * @param taglist the taglist to be initialized on.
     */
    public void initialize(Taglist taglist)
    {
        curTaglist = taglist;

        clearData();



        setState(WindowState.VIEW);
    }

    //endregion

    //region Datafield recovery

    @Override
    protected DataFieldStorage storeFields()
    {
        return DataFieldStorage.store(

        );
    }

    @Override
    protected void restoreFields(DataFieldStorage storage)
    {

        String[] fields = storage.retrieve();


    }

    //endregion

    //region inherited methods

    /**
     * @return true iff we are still in the process of creating the tracker.
     */
    protected boolean isCreating()
    {
        return curTaglist == null;
    }

    @Override
    protected void deleteItem()
    {

    }

    @Override
    protected void saveItem()
    {

    }

    //endregion

    //region Tracker Handling



    //endregion

    //region Deletion Handling



    //endregion


}