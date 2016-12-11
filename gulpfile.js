var gulp = require('gulp');
var sass = require('gulp-ruby-sass');
var autoprefixer = require('gulp-autoprefixer');
var browserSync = require('browser-sync');
var reload = browserSync.reload;

gulp.task('sass', function() {
  return sass('scss/main.scss')
    .pipe(autoprefixer({ browsers: ['> 0%'] }))
    .pipe(gulp.dest('./css'))
    .pipe(reload({ stream:true }));
});

// watch Sass files for changes, run the Sass preprocessor with the 'sass' task and reload
gulp.task('serve', ['sass'], function() {
    browserSync.init({
        server: "./"
    });

  gulp.watch('scss/**/*.scss', ['sass']);
});
