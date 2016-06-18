package com.nazeer.gallery;

import com.nazeer.gallery.Api.models.Flower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazeer on 6/18/16.
 */
public class FlowerSearchHelper {

    public static void generateSearchAddAndDeleteList(List<Flower>fullFlowersList, List<Flower>currentViewdList
            , ArrayList<Flower>addFlowerList,ArrayList<Integer>deleteSequence,String searchTerm){
        generateSearchAddlist(fullFlowersList,currentViewdList,addFlowerList,searchTerm);
        generateSearchDeleteSequence(fullFlowersList,currentViewdList,deleteSequence,searchTerm);

    }

    private static void generateSearchAddlist(
            List<Flower>fullFlowersList, List<Flower>currentViewdList
            , ArrayList<Flower>addFlowerList,String searchTerm){
        for (int i = 0; i <fullFlowersList.size() ; i++) {
            Flower currentFlower=fullFlowersList.get(i);
            if(matchesSearch(currentFlower,searchTerm)){
                if(currentViewdList.indexOf(currentFlower)==-1){
                    addFlowerList.add(currentFlower);
                }
            }
        }

    }
    private static void generateSearchDeleteSequence(
            List<Flower>fullFlowersList, List<Flower>currentViewdList
            , ArrayList<Integer>deleteSequence,String searchTerm){
        for (int i = currentViewdList.size()-1; i >=0 ; i--) {
            Flower currentFlower = currentViewdList.get(i);
            if (!matchesSearch(currentFlower, searchTerm)) {
                deleteSequence.add(i);
            }
        }
    }

    private static boolean matchesSearch(Flower flower, String searchTerm){
        if(searchTerm.length()==0)return true;
        searchTerm=searchTerm.toLowerCase();
        if(flower.getName().toLowerCase().contains(searchTerm))return true;
        if(flower.getCategory().toLowerCase().contains(searchTerm))return true;
        if(flower.getInstructions().toLowerCase().contains(searchTerm))return true;
        return false;
    }
}
