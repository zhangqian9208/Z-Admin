Vue.use(window.VueQuillEditor);
const fontSizeStyle = Quill.imports["attributors/style/size"];
fontSizeStyle.whitelist = [ "12px", "14px", "16px", "18px", "20px", "24px", "28px", "32px", "36px"];
Quill.register(fontSizeStyle, true);
const Font = Quill.imports["attributors/style/font"];
const fonts = ["SimSun", "SimHei", "Microsoft-YaHei", "KaiTi", "FangSong"];
Font.whitelist = fonts;
Quill.register(Font, true);

const Parchment = Quill.imports["parchment"];
let config = {
    scope: Parchment.Scope.BLOCK,
    whitelist: ['right', 'center', 'justify']
};
let AlignStyle = new Parchment.Attributor.Style('align', 'text-align', config);
var Align = Quill.import('attributors/style/align');
Align.whitelist = ['right', 'center', 'justify'];
Quill.register(Align, true);

Quill.register( 'modules/eeSourceBtn', window.eeSourceBtn );
Vue.component('ll-editor', {
    template: '\
        <div>\
            <quill-editor :style="style_obj"\
                v-model="content"\
                :options="editorOption"\
                @blur="onEditorBlur($event)"\
                @focus="onEditorFocus($event)"\
                @change="onEditorChange($event)"\
                @ready="onEditorReady($event)"\
            />\
            <input type="hidden" :name="name" :value="content" />\
        </div>\
    ',
    props: ['html', 'name', 'width', 'maxheight'],
    created() {
        this.content = this.html;
        window.EQuill = this;
    },
    data() {
        return {
            content: '',
            style_obj: {
                width: this.width,
                maxHeight: this.maxheight
            },
            editorOption: {
                modules: {
                    imageResize:{
                        displayStyles:{
                            backgroundColor:'black',
                            border:'none',
                            color:'white'
                        },
                        modules:['Resize','DisplaySize','Toolbar']
                    },
                    eeSourceBtn: eeSourceBtn,
                    toolbar: [
                        ["bold", "italic", "underline", "strike"], // ?????? ?????? ????????? ?????????
                        ["blockquote", "code-block"], // ??????  ?????????
                        [{ header: 1 }, { header: 2 }], // 1???2 ?????????
                        [{ list: "ordered" }, { list: "bullet" }], // ?????????????????????
                        [{ script: "sub" }, { script: "super" }], // ??????/??????
                        [{ indent: "-1" }, { indent: "+1" }], // ??????
                        [{ direction: "rtl" }], // ????????????
                        [{ size: fontSizeStyle.whitelist }],
                        [{ font: fonts }],
                        [{ header: [1, 2, 3, 4, 5, 6] }], // ??????
                        [{ color: [] }, { background: [] }], // ?????????????????????????????????
                        [{ align: [] }], // ????????????
                        ["clean"], // ??????????????????
                        ["image"],  //????????????
                        // ["image", "video"], // ????????????????????????
                    ],
                },
                placeholder: "",
            },
        }
    },
    watch: {
        html(val) {
            this.content = val;
        },
    },
    methods: {
        onEditorBlur(quill) {
            // console.log("editor blur!", quill);
        },
        // ??????????????????
        onEditorFocus(quill) {
            // console.log("editor focus!", quill);
        },
        // ????????????????????????
        onEditorReady(quill) {
            // console.log("editor ready!", quill);
        },
        // ??????????????????
        onEditorChange({ quill, html, text }) {
            this.content = html;
            this.$emit("getcontent", html);
        },
    },
})