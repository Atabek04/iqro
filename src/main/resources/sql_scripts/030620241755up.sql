CREATE TABLE "question"(
                           "id" SERIAL NOT NULL,
                           "text" TEXT NOT NULL,
                           "quiz_id" INTEGER NOT NULL
);
ALTER TABLE
    "question" ADD PRIMARY KEY("id");
CREATE TABLE "submission"(
                             "id" SERIAL NOT NULL,
                             "assignment_id" INTEGER NOT NULL,
                             "user_id" INTEGER NOT NULL,
                             "data" BIGINT NOT NULL,
                             "grade" INTEGER NOT NULL,
                             "date" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
                             "status" SMALLINT NOT NULL
);
ALTER TABLE
    "submission" ADD PRIMARY KEY("id");
CREATE TABLE "quiz_result"(
                              "id" SERIAL NOT NULL,
                              "attempt_id" INTEGER NOT NULL,
                              "question_id" INTEGER NOT NULL,
                              "selected_option_id" INTEGER NOT NULL,
                              "is_correct" BOOLEAN NOT NULL
);
ALTER TABLE
    "quiz_result" ADD PRIMARY KEY("id");
CREATE TABLE "user"(
                       "id" SERIAL NOT NULL,
                       "name" VARCHAR(255) NOT NULL,
                       "surname" VARCHAR(255) NOT NULL,
                       "email" VARCHAR(255) NOT NULL,
                       "password" VARCHAR(255) NOT NULL,
                       "role" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "user" ADD PRIMARY KEY("id");
CREATE TABLE "lesson"(
                         "id" SERIAL NOT NULL,
                         "title" VARCHAR(255) NOT NULL,
                         "video_link" VARCHAR(255) NOT NULL,
                         "notes" TEXT NOT NULL,
                         "module_id" BIGINT NOT NULL
);
ALTER TABLE
    "lesson" ADD PRIMARY KEY("id");
CREATE TABLE "module"(
                         "id" SERIAL NOT NULL,
                         "name" VARCHAR(255) NOT NULL,
                         "course_id" INTEGER NOT NULL
);
ALTER TABLE
    "module" ADD PRIMARY KEY("id");
ALTER TABLE
    "module" ADD CONSTRAINT "module_name_unique" UNIQUE("name");
CREATE TABLE "assignment"(
                             "id" SERIAL NOT NULL,
                             "title" VARCHAR(255) NOT NULL,
                             "module_id" INTEGER NOT NULL,
                             "deadlline" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "assignment" ADD PRIMARY KEY("id");
CREATE TABLE "quiz_attempt"(
                               "id" SERIAL NOT NULL,
                               "user_id" INTEGER NOT NULL,
                               "quiz_id" INTEGER NOT NULL,
                               "date" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "quiz_attempt" ADD PRIMARY KEY("id");
CREATE TABLE "lesson_comment"(
                                 "id" BIGINT NOT NULL,
                                 "lesson_id" INTEGER NOT NULL,
                                 "user_id" INTEGER NOT NULL,
                                 "text" TEXT NOT NULL,
                                 "date" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "lesson_comment" ADD PRIMARY KEY("id");
CREATE TABLE "enrollment"(
                             "id" SERIAL NOT NULL,
                             "user_id" INTEGER NOT NULL,
                             "course_id" BIGINT NOT NULL
);
ALTER TABLE
    "enrollment" ADD PRIMARY KEY("id");
CREATE TABLE "assignment_comment"(
                                     "id" SERIAL NOT NULL,
                                     "assignment_id" INTEGER NOT NULL,
                                     "user_id" INTEGER NOT NULL,
                                     "text" TEXT NOT NULL,
                                     "date" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "assignment_comment" ADD PRIMARY KEY("id");
CREATE TABLE "course"(
                         "id" SERIAL NOT NULL,
                         "name" VARCHAR(255) NOT NULL,
                         "description" TEXT NOT NULL,
                         "progress" INTEGER NOT NULL,
                         "last_updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
                         "status" SMALLINT NOT NULL
);
ALTER TABLE
    "course" ADD PRIMARY KEY("id");
ALTER TABLE
    "course" ADD CONSTRAINT "course_name_unique" UNIQUE("name");
CREATE TABLE "question_option"(
                                  "id" SERIAL NOT NULL,
                                  "question_id" INTEGER NOT NULL,
                                  "text" VARCHAR(255) NOT NULL,
                                  "is_correct" BOOLEAN NOT NULL DEFAULT '0'
);
ALTER TABLE
    "question_option" ADD PRIMARY KEY("id");
CREATE TABLE "quiz"(
                       "id" SERIAL NOT NULL,
                       "title" VARCHAR(255) NOT NULL,
                       "module_id" INTEGER NOT NULL
);
ALTER TABLE
    "quiz" ADD PRIMARY KEY("id");
ALTER TABLE
    "assignment" ADD CONSTRAINT "assignment_module_id_foreign" FOREIGN KEY("module_id") REFERENCES "module"("id");
ALTER TABLE
    "lesson_comment" ADD CONSTRAINT "lesson_comment_lesson_id_foreign" FOREIGN KEY("lesson_id") REFERENCES "lesson"("id");
ALTER TABLE
    "lesson_comment" ADD CONSTRAINT "lesson_comment_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE
    "assignment_comment" ADD CONSTRAINT "assignment_comment_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE
    "module" ADD CONSTRAINT "module_course_id_foreign" FOREIGN KEY("course_id") REFERENCES "course"("id");
ALTER TABLE
    "quiz_result" ADD CONSTRAINT "quiz_result_selected_option_id_foreign" FOREIGN KEY("selected_option_id") REFERENCES "question_option"("id");
ALTER TABLE
    "enrollment" ADD CONSTRAINT "enrollment_course_id_foreign" FOREIGN KEY("course_id") REFERENCES "course"("id");
ALTER TABLE
    "enrollment" ADD CONSTRAINT "enrollment_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE
    "quiz" ADD CONSTRAINT "quiz_module_id_foreign" FOREIGN KEY("module_id") REFERENCES "module"("id");
ALTER TABLE
    "assignment_comment" ADD CONSTRAINT "assignment_comment_assignment_id_foreign" FOREIGN KEY("assignment_id") REFERENCES "assignment"("id");
ALTER TABLE
    "quiz_attempt" ADD CONSTRAINT "quiz_attempt_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE
    "quiz_result" ADD CONSTRAINT "quiz_result_question_id_foreign" FOREIGN KEY("question_id") REFERENCES "question"("id");
ALTER TABLE
    "submission" ADD CONSTRAINT "submission_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE
    "lesson" ADD CONSTRAINT "lesson_module_id_foreign" FOREIGN KEY("module_id") REFERENCES "module"("id");
ALTER TABLE
    "question" ADD CONSTRAINT "question_quiz_id_foreign" FOREIGN KEY("quiz_id") REFERENCES "quiz"("id");
ALTER TABLE
    "submission" ADD CONSTRAINT "submission_assignment_id_foreign" FOREIGN KEY("assignment_id") REFERENCES "assignment"("id");
ALTER TABLE
    "question_option" ADD CONSTRAINT "question_option_question_id_foreign" FOREIGN KEY("question_id") REFERENCES "question"("id");
ALTER TABLE
    "quiz_result" ADD CONSTRAINT "quiz_result_attempt_id_foreign" FOREIGN KEY("attempt_id") REFERENCES "quiz_attempt"("id");