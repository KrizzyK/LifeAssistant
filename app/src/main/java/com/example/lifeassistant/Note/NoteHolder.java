package com.example.lifeassistant.Note;

import java.util.List;

public class NoteHolder {
    //private ArrayList<Note> list;
    private List<Note> list;
    public NoteHolder(List<Note> list) {
        this.list = list;
    }
    public void addNote(Note note) {
        list.add(note);
    }
    public void removeNote(Note note) {
        list.remove(note);
    }
    public void removeNote(int index) {
        list.remove(index);
    }
    public List<Note> getList() {
        return list;
    }
    public void setList(List<Note> list) {
        this.list = list;
    }
    public void setNote(int index, Note note) {
        list.set(index, note);
    }
    public void addSomeRandomStuff() {
        list.add(new Note("Siema", "Nowa notka"));
        list.add(new Note("", ""));
        list.add(new Note("Siema3", "Nowa notkajeimhfsoeh sefuihefuisfeuigdszgzg ci bvnuisdhisdhfsuifmcsuihdfcsuimchuishmfcsuicmhfuishmcuifsmhcfuishmcfuishmcuihsdfmcuismhcfuishmcfsuihmcfuishmfcuismhcfuismhcfuismhfcuismhcfuismhcfuismhcuismhcfuismhcfsuimchfsuicmhfsiesiefih sreuifoemfeuicmehsuicfesomhcsefuicsefuiefhiufephefuimhcuiehmfceuihmceuichmeues"));
        list.add(new Note("Siema4", "Nowa notka"));
        list.add(new Note("Siema5", ""));
        list.add(new Note("", "Nowa notka"));
        list.add(new Note("Siema7", "Nowa notka"));
        list.add(new Note("Siema8nefismenimpfsiempjuifmjiespcjmeisocjfmiesjoie,jciescjsf,ieospcse,sopjsefcsece", "Nowa notka"));
        list.add(new Note("Siema9", "Nowa notka"));
        list.add(new Note("Siema10", "Nowa notka"));
        list.add(new Note("Siema11iaowp,jeaioj,ioe,jioefj,eioefj,iofej,esio,fsijpjsiofj,so", "Nowa notkaiadpo,wjwaioj,dwaioj,dsioaj,aods,ao"));
        list.add(new Note("Siema12", "Nowa notka"));
    }
}
