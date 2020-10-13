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

public class ImageChooser extends ChoiceBox {
  public static final String[] IMAGE_PROPERTIES = {"StarryNightImage","PolkaDotImage"};
  public static final CellColors[] CELL_PHOTOS = {CellColors.STARRY_NIGHT,CellColors.POLKA_DOTS};
  public static final String[] CELL_PHOTO_NAMES = {"Starry Night", "Polka Dots"};
  private Paint[] myImagePatterns = new Paint[IMAGE_PROPERTIES.length];
  private ObservableList myPhotos;
  private CellColors myChosenImage;

  public ImageChooser(ResourceBundle resources){
    super();

    getImagesFromResources(resources);

    this.setTooltip(new Tooltip("Select a Photo: "));

    myPhotos = FXCollections.observableArrayList(Arrays.asList(CELL_PHOTO_NAMES));
    this.setItems(myPhotos);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        myChosenImage = CELL_PHOTOS[newValue.intValue()];
      }
    });
  }

  public CellColors getChosenImage(){
    return myChosenImage;
  }

  private void getImagesFromResources(ResourceBundle resources){
    for(int fileNum=0;fileNum<myImagePatterns.length;fileNum++){
      String filePath = resources.getString(IMAGE_PROPERTIES[fileNum]);
      CELL_PHOTOS[fileNum].setCellImage(filePath);
    }
  }


}
