# School Management System

This is a small web project for School Management System built using Java Enterprise Edition (Jakarta EE) and JSP (JavaServer Pages), with Bootstrap 5 for the front-end design.

## Overview

The School Management System aims to streamline various administrative tasks within a school environment. It provides functionalities for managing student information, course enrollment and more.

## Features

- **Student Management**: Add, edit, and delete student records. View student details including personal information.
- **Course Management**: Add, edit, and delete course records. View course details.
- **Grade Management**: Add, edit, and delete course grade records. View course grades details for each student.
- **Admin Management**: Add, edit, and delete admin records. View admin details.

## TODO

Some features to add :
- **Teacher Management**: Manage teacher profiles, including contact details and subjects taught.
- **Course Schedules**: manage course schedules.
- **Attendance Tracking**: Track student attendance and generate attendance reports.
- **User Authentication**: Secure login system for administrators, teachers, and students with role-based access control.
- **Teacher Dashboard**: Teachers can view their assigned courses, manage student attendance, and view course schedules.
- **Student Dashboard**: Students can view their personal information, course enrollment, attendance records, and grades.

## Technologies Used

- **Java EE (Jakarta EE)**: Provides the infrastructure for developing and deploying enterprise-level applications.
- **JSP (JavaServer Pages)**: Used for dynamic web page generation, allowing for the creation of dynamic, data-driven web applications.
- **JSTL (JavaServer Pages Standard Tag Library)**: Provides a set of tags to simplify the presentation of dynamic content in JSP pages.
- **Bootstrap 5**: Front-end framework for designing responsive and mobile-first websites.
- **MySQL (or any preferred database)**: Database management system for storing and managing school data.

## Getting Started

1. **Prerequisites**: Ensure you have Java JDK 8, a Java IDE (such as IntelliJ IDEA or Eclipse), Apache Tomcat V9 (or any servlet container), and MySQL (or any preferred database) installed on your system.

2. **Clone the Repository**: `git clone https://github.com/zak-ask/school_management.git`

3. **Database Setup**: Import the database schema provided in `/src/main/resources/db/init-db.sql` to set up the required tables and relationships.

4. **Configuration**: Update the database connection details in the `application.properties` file.

5. **Run the Application**: Deploy the application to your servlet container (e.g., Apache Tomcat) and access it through the browser.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

Special thanks to [Bootstrap](https://getbootstrap.com/) for the front-end design framework and the open-source community for their invaluable contributions.

