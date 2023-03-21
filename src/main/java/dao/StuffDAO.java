package dao;

import models.Stuff;

import java.util.List;

public interface StuffDAO {

    //знаю что подписать надо но мне лень. и так всё понятно))

    void create(Stuff stuff);

    Stuff readById(int id);

    List<Stuff> readAll();

    void updateStuffEntity(Stuff stuff);

    void deleteStuffEntity(Stuff stuff);
}