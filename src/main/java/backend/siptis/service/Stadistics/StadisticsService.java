package backend.siptis.service.Stadistics;

import backend.siptis.commons.ServiceAnswer;

public interface StadisticsService {

    ServiceAnswer StudentsByCareerStadistics();

    ServiceAnswer UserByAreaStadistics();

    ServiceAnswer ProyectsByAreaAndCareer(int idCareer);

    ServiceAnswer getStudentsByYear();

}
