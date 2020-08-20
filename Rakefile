require_relative 'lib/geomerative/version'


task default: [:compile, :gem]

desc 'Build gem'
task :gem do
  sh "jgem build geomerative.gemspec"
end

desc 'Compile'
task :compile do
  sh "mvn package"
  sh "mv target/geomerative.jar lib"
end

desc 'clean'
task :clean do
  Dir['./**/*.%w{jar gem}'].each do |path|
    puts "Deleting #{path} ..."
    File.delete(path)
  end
  FileUtils.rm_rf('./target')
end
