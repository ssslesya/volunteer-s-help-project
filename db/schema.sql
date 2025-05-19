CREATE TABLE Branches (
    branch_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type ENUM('банк', 'почта', 'пункт') NOT NULL,
    address VARCHAR(500) NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    working_hours JSON NOT NULL
);

CREATE TABLE Services (
    service_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    branch_id UUID NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES Branches(branch_id) ON DELETE CASCADE
);

CREATE TABLE BranchLoad (
    load_id UUID PRIMARY KEY,
    branch_id UUID NOT NULL,
    current_load INTEGER NOT NULL,
    avg_wait_time INTEGER NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES Branches(branch_id) ON DELETE CASCADE
);

CREATE TABLE VisitStatistics (
    stat_id UUID PRIMARY KEY,
    branch_id UUID NOT NULL,
    date DATE NOT NULL,
    visits INTEGER NOT NULL,
    avg_duration INTEGER NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES Branches(branch_id) ON DELETE CASCADE
);


CREATE TABLE Recommendations (
    recommendation_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    branch_id UUID NOT NULL,
    score FLOAT NOT NULL,
    generated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES Branches(branch_id) ON DELETE CASCADE
);

CREATE TABLE Users (
    user_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE Notifications (
    notification_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    message TEXT NOT NULL,
    status ENUM('отправлено', 'доставлено', 'ошибка') NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE UsersAuth (
    user_id UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    last_login TIMESTAMP
);

CREATE TABLE Roles (
    role_id UUID PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE UserRoles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES UsersAuth(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Roles(role_id) ON DELETE CASCADE
);

