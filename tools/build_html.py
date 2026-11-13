# Ubacuje podatke o vozovima (trips.json) u template.html i pravi app/src/main/assets/index.html
import pathlib
root=pathlib.Path(__file__).resolve().parent
t=(root/'template.html').read_text(encoding='utf-8')
d=(root/'trips.json').read_text(encoding='utf-8')
(root.parent/'app/src/main/assets/index.html').write_text(t.replace('__DATA__',d),encoding='utf-8')
print('OK')
