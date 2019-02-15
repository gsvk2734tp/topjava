package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles(profiles = {Profiles.POSTGRES_DB, Profiles.DATAJPA})
public class DATAJPAUserServiceTest extends UserServiceTest {
}
