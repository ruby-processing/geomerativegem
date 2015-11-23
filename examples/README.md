The sketches here seem to be require the `--nojruby` flag to run, which seems to be Catch-22, since conventionally it was thought you needed an installed jruby to use gems. However it works absolutely fine for me on my linux box, suggesting that jruby-complete-9.0.4.0 is respecting the `GEM_PATH` and or `GEM_HOME` environmental variables set in my `.bashrc` Archlinux or `.profile` Mint (Ubuntu/Debian).

```bash
export GEM_HOME="/home/tux/.gem/ruby/2.2.0"
export GEM_PATH="/home/tux/.gem/ruby/2.2.0"
export PATH="${PATH}:${GEM_PATH}/bin"
```
