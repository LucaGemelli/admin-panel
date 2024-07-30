CREATE TABLE IF NOT EXISTS admin_panel_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS admin_panel_permission (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS admin_panel_user_permission (
    user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    FOREIGN KEY (user_id) REFERENCES admin_panel_user(id),
    FOREIGN KEY (permission_id) REFERENCES admin_panel_permission(id)
);
