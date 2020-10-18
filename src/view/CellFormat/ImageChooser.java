package view.CellFormat;

import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Paint;

/**
 * The Image Chooser class allows the user to choose an image to place on all the cells with
 * a chosen state.
 */
public class ImageChooser extends ChoiceBox {
  public static final String[] IMAGE_PROPERTIES = {"StarryNightImage","PolkaDotImage"};
  public static final CellFill[] CELL_PHOTOS = CellImages.values();
  private String[] myImageNames = new String[IMAGE_PROPERTIES.length];
  private ObservableList myPhotos;
  private CellFill myChosenImage;

  public ImageChooser(ResourceBundle resources){
    super();

    this.setId("image-chooser");
    this.setTooltip(new Tooltip("Select a Photo: "));

    getImageNamesFromProperty(resources);

    myPhotos = FXCollections.observableArrayList(Arrays.asList(myImageNames));
    this.setItems(myPhotos);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        myChosenImage = CELL_PHOTOS[newValue.intValue()];
      }
    });
  }

  public void getImageNamesFromProperty(ResourceBundle resources){
    for(int imageNum=0; imageNum<myImageNames.length;imageNum++){
      myImageNames[imageNum]=resources.getString(CELL_PHOTOS[imageNum].toString());
    }
  }

  public CellFill getChosenImage(){
    return myChosenImage;
  }

  public void setMyChosenImage(CellFill image){ myChosenImage = image; }


}
