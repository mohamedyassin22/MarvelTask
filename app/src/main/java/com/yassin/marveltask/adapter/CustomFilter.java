package com.yassin.marveltask.adapter;

import android.widget.Filter;

import com.yassin.marveltask.model.Characters;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    SearchCharactersAdapter adapter;
    ArrayList<Characters> filterList;

    public CustomFilter(ArrayList<Characters> filterList, SearchCharactersAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if (constraint != null && constraint.length() > 0) {
            //CHANGE TO UPPER
            constraint = constraint.toString().toUpperCase();
            //STORE OUR FILTERED CHARACTERS
            ArrayList<Characters> filteredCharacter = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {
                //CHECK
                if (filterList.get(i).getName().toUpperCase().startsWith((String) constraint)) {
                    //ADD PLAYER TO FILTERED CHARACTERS
                    filteredCharacter.add(filterList.get(i));
                }
            }
            results.count = filteredCharacter.size();
            results.values = filteredCharacter;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.characters = (ArrayList<Characters>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
