DROP TABLE IF EXISTS tasksBloc;

CREATE TABLE tasksBloc(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  descripcion VARCHAR(250) NOT NULL,
  estado VARCHAR(250) NOT NULL
);

INSERT INTO tasksBloc(descripcion, estado) VALUES
  ('Primera tarea de prueba', 'Completada'),
  ('Segunda tarea de prueba', 'Pendiente');