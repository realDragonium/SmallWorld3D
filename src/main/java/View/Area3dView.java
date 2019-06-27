package View;

import Controller.AreaController;
import Controller.FicheController;
import Observable.AreaObservable;
import Observer.AreaObserver;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
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

public class Area3dView implements AreaObserver {

    MeshView area;
    AreaController areaCon;

    public Area3dView(Node area, AreaController areaCon){
        this.area = (MeshView) area;
        this.areaCon = areaCon;
        init();
        Mesh mesh = ((MeshView) area).getMesh();

        Point3D boundsInScene = area.localToScene(0,0,0);
        System.out.println(boundsInScene);
        areaCon.register(this);

    }


    public void init(){
        area.setOnMouseClicked(e -> {
            PickResult pr = e.getPickResult();
            areaCon.showInfo();

            //System.out.println("areaPoints.put(" + area.getId() + " ,new Translate(" + Math.round(pr.getIntersectedPoint().getX() * 100) + "," + Math.round(pr.getIntersectedPoint().getY() * 100) + "," + Math.round( pr.getIntersectedPoint().getZ()* 100) + "));");
        });
        area.setOnMouseEntered(e -> areaCon.hoverEntered());
        area.setOnMouseExited(e -> areaCon.hoverExited());
    }

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


    }
}
