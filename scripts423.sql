SELECT s.name, s.age, f.name AS facultyName
FROM student s
JOIN faculty f ON s.facultyId = f.id

SELECT s.name, s.age
FROM student s
JOIN avatar a ON s.id = a.studentId

