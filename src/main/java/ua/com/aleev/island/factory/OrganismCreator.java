package ua.com.aleev.island.factory;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;
import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.exception.GameException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class OrganismCreator {
    public OrganismCreator() {
    }

    public static Organism[] createPrototype(Class<?>[] TYPES) {
        Organism[] organisms = new Organism[TYPES.length];
        int index = 0;
        for (Class<?> type : TYPES) {
            if (type.isAnnotationPresent(OrganismTypeData.class)) {
                OrganismTypeData typeData = type.getAnnotation(OrganismTypeData.class);
                String name = typeData.name();
                String icon = typeData.icon();
                double weight = typeData.maxWeight();
                Limit limit = new Limit(
                        typeData.maxWeight(),
                        typeData.maxCount(),
                        typeData.maxSpeed(),
                        typeData.maxFood()
                );
                organisms[index++] = bornOrganism(type, name, icon, weight, limit);
            }
        }
        return organisms;
    }

    private static Organism bornOrganism(Class<?> type, String name, String icon, double weight, Limit limit) {
        try {
            Constructor<?> constructor = type.getConstructor(String.class, String.class, double.class, Limit.class);
            return (Organism) constructor.newInstance(name, icon, weight, limit);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                 InstantiationException e) {
            throw new GameException("not found Entity constructor ", e);
        }
    }
}
