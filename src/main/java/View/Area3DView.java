package View;

import Controller.AreaController;
import Controller.Controller3D;
import Controller.FicheController;
import Controller.GameController;
import Objects.FXMLLOADER;
import Observable.AreaObservable;
import Observer.AreaObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import Enum.AreaColor;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Translate;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class Area3DView implements AreaObserver {

    private MeshView area;
    private AreaController areaCon;
    private Group number = new Group();
    private int currentNumber;
    private Group map;

    public Area3DView(Node area, AreaController areaCon, Group map){
        this.area = (MeshView) area;
        this.areaCon = areaCon;
        this.map = map;
        init();
        areaCon.register(this);
    }


    public void init(){
        area.setOnMouseClicked(e -> {
            //PickResult pr = e.getPickResult();
            areaCon.showInfo();
//            createRaceFiche("ratten");
            //System.out.println(area.getId());
            //System.out.println("propPoints.put(\"" + area.getId() + "\" ,new Translate(" + Math.round(pr.getIntersectedPoint().getX() * 100) + "," + Math.round(pr.getIntersectedPoint().getY() * 100) + "," + Math.round( pr.getIntersectedPoint().getZ()* 100) + "));");
        });
        area.setOnMouseEntered(e -> {
            if(currentNumber != 0) showNumber();
            areaCon.hoverEntered();
        });

        area.setOnMouseExited(e -> {
            hideNumber();
            areaCon.hoverExited();
        });
        areaCon.createNumber(number);
        map.getChildren().add(number);
//        con3d.add3dObject(number);
    }

    private void showNumber() {
        number.setVisible(true);
    }

    private void hideNumber(){
        number.setVisible(false);
    }

//    public FicheController createRaceFiche(String race){
//        FicheController ficheCon = new FicheController(1, "Ghost");
//        new fiche3dView(ficheCon, map, race);
//        return ficheCon;
//    }


    @Override
    public void update(AreaObservable ao) {
        if(ao.getActive()){
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.GREY);
            area.setMaterial(material);
        }
        else if(ao.isHovering()){
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.WHITE);
            area.setMaterial(material);
        }
        else if(ao.isShowing()){
            Color color = ((PhongMaterial)area.getMaterial()).getSpecularColor().darker();
            PhongMaterial material = (PhongMaterial)area.getMaterial();
            material.setDiffuseColor(color);
            area.setMaterial(material);
        }
        else{
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(AreaColor.valueOf(area.getId().substring(0, (area.getId().length() - 4))).getColor());
            area.setMaterial(material);
        }

        if(ao.getNumberOfFiches() == 0){
            number.setVisible(false);
        }

        else{
            if(ao.getNumberOfFiches() != currentNumber) {
                currentNumber = ao.getNumberOfFiches();
                areaCon.setNumber(currentNumber);
                number.setTranslateX(ao.getAreaPoint().getX());
                number.setTranslateY(ao.getAreaPoint().getY() - 100 - ao.getNumberOfFiches() * 10);
                number.setTranslateZ(ao.getAreaPoint().getZ());
            }
        }

    }
}
