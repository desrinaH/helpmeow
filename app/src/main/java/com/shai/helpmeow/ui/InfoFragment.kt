package com.shai.helpmeow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.shai.helpmeow.R
import com.shai.helpmeow.data.BreedCat

class InfoFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        val breedGridView: GridView = view.findViewById(R.id.storyRecyclerView)
        breedGridView.adapter = BreedAdapter(getBreedData())
        breedGridView.numColumns = 3
        breedGridView.verticalSpacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
        breedGridView.horizontalSpacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)

        return view
    }

    private fun getBreedData(): List<BreedCat> {
        val breedList: MutableList<BreedCat> = mutableListOf()
        breedList.add(
            BreedCat(
                R.drawable.img_breed01,
                "Abyssinian",
                "Kucing abisinia adalah salah satu ras kucing berbulu pendek tertua yang pernah diketahui. Abisinia menyerupai lukisan dan patung yang berasal dari zaman Mesir Kuno. Perdagangan ras kucing ini dilarang oleh bangsa Mesir. Selain itu, bangsa Mesir juga mendirikan kuil khusus untuk memuja abisinia. Mumi abisinia banyak ditemukan pada makam-makam bangsa Mesir Kuno. Pada saat bangsa Romawi menguasai Mesir, abisinia pernah dibawa ke Inggris dengan tujuan melindungi ladang gandum dari tikus.",
                "Rp 17 jt - Rp 32 jt",
                "4 kg - 5  kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed02,
                "American Bobtail",
                "Kucing ekor bundel amerika adalah salah satu ras kucing domestik yang berasal dari Amerika Serikat. Terlepas dari penampilannya, American Bobtails sering disebut sebagai \"Golden Retriever dari dunia kucing\", karena kepribadian mereka yang manis, penuh kasih sayang, dan jinak.",
                "Rp 8 jt - Rp 17 jt",
                "3 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed03,
                "American Curl",
                "Kucing telinga ikal amerika adalah salah satu ras kucing akibat mutasi genetik alami yang berasal dari Lakewood,  California, Amerika Serikat. Sesuai dengan namanya, kucing telinga ikal amerika memiliki ciri khas pada bentuk telinganya yang ikal atau melengkung.",
                "Rp 14 jt - Rp 24 jt",
                "3 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed04,
                "American Shorthair",
                "Kucing bulu pendek amerika adalah salah satu ras kucing dari Amerika Utara yang masih merupakan keturunan  dari ras kucing bulu pendek eropa dan kucing bulu pendek britania raya. kucing ini memiliki moncong yang berbentuk seperti kotak, telinga yang, bulu yang pendek, tebal, padat, dan sedikit kaku, serta memiliki kaki dan cakar yang kuat. Bulu pendek amerika memiliki semua pola dan warna bulu, kecuali colorpoint. Bulunya akan  menjadi tebal jika musim dingin tiba, dan rontok jika musim semi tiba.",
                "Rp 12 jt - Rp 21 jt",
                "3 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed05,
                "American Wirehair",
                "Kucing bulu dawai amerika adalah ras kucing domestik yang berasal dari bagian utara New York, Amerika Serikat. Ras kucing ini adalah ras kucing akibat mutasi genetik yang spontan. Salah satu ciri khasnya adalah bulunya yang berkerut. Kucing ini juga dihasilkan dari persilangan ras kucing bulu pendek amerika, agar memperkuat sifat genetiknya. Kucing ini tidak membutuhkan perawatan khusus. Kucing ini diketahui adalah kucing yang sehat dan tidak memiliki kelainan genetik. Harapan hidup rata-rata dapat mencapai 12-14 tahun.",
                "Rp 11 jt - Rp 21 jt",
                "3.5 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed06,
                "Balinese",
                "Kucing bali adalah salah satu ras kucing alami akibat mutasi genetik pada ras siam. Perbedaan antara ras bali dengan ras siam terletak pada ukuran bulunya saja, kucing bali berbulu sedang, sedangkan kucing siam berbulu pendek. Ras  ini adalah ras kucing oriental, yaitu memiliki tubuh yang panjang dan langsing. Pada sekitar tahun 1920-an, kucing bulu panjang siam dianggap sebagai kucing yang aneh, sehingga banyak yang menjualnya dengan harga yang murah. Tak ada satu pun usaha untuk melakukan program pembiakan terhadap ras kucing ini. Pada sekitar tahun 1950-an, barulah muncul usaha untuk mengembangbiakan ras ini agar dapat terpisah dengan ras siam menjadi ras tersendiri.",
                "Rp 14 jt - Rp 20 jt",
                "Jantan : 3 kg - 5 kg\n" +
                        "Betina : 3 kg - 4 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed07,
                "Bengal",
                "Kucing bengal adalah keturunan keempat dari hasil persilangan antara kucing american shorthair dengan kucing asian leopard. Kucing ini berasal dari California, Amerika Serikat. Meskipun tergolong kucing hutan, tetapi ras bengal termasuk hewan yang banyak digemari dan dijadikan hewan peliharaan. Kucing ini memiliki ukuran badan yang panjang dengan otot-otot pada tubuhnya yang kuat. Ia memiliki tulang yang besar dan tebal, serta bulu yang tebal, rapat dan halus. Dalam proses pemeliharaannya, kucing ini memerlukan perawatan khusus pada bulunya, yaitu dengan digosok secara lembut menggunakan sikat yang halus. Populasi kucing bengal belum begitu banyak sehingga harganya tergolong tinggi",
                "Rp 5 jt - Rp 75 jt",
                "Jantan : 4.5 kg - 6.9 kg\n" +
                        "Betina : 3.6 kg - 5.5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed08,
                "Birman",
                "Kucing birman adalah salah satu ras kucing alami yang berasal dari Prancis. Kucing ini merupakan ras semi-longhair dengan warna rambut kegelapan di sekitar wajah, kaki, telinga, dan ekor, serta warna tubuh yang pucat. Birman adalah jenis kucing yang besar dengan tubuh rimbun dan tungkai pendek. Kucing Birman memiliki mata berwarna biru dengan kaki-kaki berwarna putih bersih. rambut kaki di depan hanya menutupi tungkai, tetapi di bagian belakang bisa lebih panjang. Kepalanya lebar dan bundar dengan telinga berukuran sedang. Varian kucing Birman memiliki warna yang berbeda-beda.",
                "Rp 6 jt - Rp 14 jt",
                "4 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed09,
                "Bombay",
                "Kucing bombay adalah salah satu ras kucing domestik yang merupakan hasil persilangan kucing bulu pendek amerika dengan kucing burma. Jenis kucing Bombay ini berukuran sedang, dengan lapisan rambut berwarna hitam pekat yang mengkilat, yang berkilau seperti kulit perlak. Bentuk kepalanya membulat, dengan telinga yang juga membulat dan moncong lebar yang tumpul. Mata berwarna keemasan atau coklat seperti perunggu yang indah memiliki jarak yang cukup dan membuatnya terlihat ekspresif. Badannya teguh dan berotot, dengan punggung yang lurus. Kulit hidung dan kelopak matanya hitam, dan telapak kakinya pun hitam atau coklat tua.",
                "Rp 7 jt - Rp 28 jt",
                "Jantan : > 5 kg\n" +
                        "Betina : 3 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed10,
                "British Shorthair",
                "Kucing British bulu pendek britania adalah ras kucing berbadan besar yang bisa dideskripsikan sebagai kucing yang memiliki tubuh pendek gempal. Kontur tubuhnya bundar, wajah dan pipi, telinga, mata, dan kepala. Hidungnya yang pesek terletak di atas dagu yang kokoh dengan dada yang dalam serta ekornya yang pendek, memberi kesan kuat dan kokoh. Rambut-rambutnya pendek dan tebal tetapi tidak mengembang dan memiliki lebih dari 100 kombinasi warna dan pola",
                "Rp 20 jt - Rp 40 jt",
                "3 kg - 8 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed11,
                "Burmese",
                "Kucing burma adalah salah satu ras kucing yang berasal dari Burma. Harapan hidup ras burma dapat mencapai 16-18 tahun. Kucing ini merupakan kucing dengan ukuran sedang, kepalanya membulat, namun tubuhnya cenderung berotot. Kucing jenis ini tidak sebesar dan sekokoh British Shorthair, dan tidak seramping jenis kucing Siam. Matanya besar dan berkilau, seringkali berwarna kuning, yang dapat berubah ketika cahaya di sekitarnya berubah juga. Ekornya kurus dan ujungnya berbentuk seperti kuas lukis. Rambut lapisan pelindung luarnya merupakan bagian yang paling membedakan jenis kucing ini, yakni rambut yang pendek, halus dan mengkilat. Kucing Burmese ini dapat memiliki 10 jenis warna berbeda, namun di setiap variannya, rambut bagian bawah akan jauh lebih ringan ketimbang punggungnya dan perubahan warnanya ini akan terlihat halus.",
                "Rp 8 jt - Rp 30 jt",
                "4 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed12,
                "Burmilla",
                "Burmilla adalah salah satu ras kucing yang terbentuk dari persilangan antara kucing burma dengan chinchilla. Kucing jenis ini berukuran sedang, serupa dengan kucing Burma. Struktur tulangnya kokoh, badannya berotot dan punggungnya lurus. Kucing berjenis kelamin betina jauh lebih kecil dan lebih menarik ketimbang kucing yang jantan. Jenis kepalanya agak membulat, dengan ujung yang cenderung lancip, dan telinganya lebar. Warna mata kucing ini kuning keemasan bahkan mungkin juga berwarna hijau. Lapisan rambut luarnya pendek dan mengikuti bentuk tubuh. Lapisan rambut luar yang cenderung pucat dengan ujung berwarna gelap di sekujur tubuhnya. Dahinya memiliki fitur yang dilihat sepintas mirip dengan bentuk huruf M.",
                "Rp 7 jt - Rp 16 jt",
                "3 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed13,
                "Chartreux",
                "Kucing chartreux adalah kucing berbadan besar dan berotot dengan bulu yang pendek. Chartreux dikenal sebagai kucing dengan bulu pendek dua lapis yang berwarna biru. Bulunya ini tahan dengan air. Mata chartreux berbentuk oval dan berwarna tembaga. Chartreux wajahnya terlihat seperti tersenyum, karena chartreux memiliki struktur tengkorak dan rahang yang sedikit berbeda. Chartreux adalah kucing berbadan besar dan berotot dengan bulu yang pendek. Chartreux dikenal sebagai kucing dengan bulu pendek dua lapis yang berwarna biru. Bulunya ini tahan dengan air. Mata chartreux berbentuk oval dan berwarna tembaga.\n",
                "Rp 7 jt - Rp 21 jt",
                "Jantan : 4.5 kg - 7 kg\n" +
                        "Betina : 2.7 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed14,
                "Chausie",
                "Kucing chausie adalah salah satu ras kucing hibrida yang berasal dari Amerika Serikat. Chausie merupakan hasil persilangan dari kucing hutan dengan kucing domestik yang memiliki bulu pendek, bertubuh sedang, panjang, dan langsing, dengan berat badan jantan sekitar 4.5-7.5 kg, dan betina 3.4–5 kg. Chausie adalah kucing yang handal dalam hal melompat dan berlari, dikarenakan tubuhnya yang panjang.\n",
                "Rp 14 jt - Rp 35 jt",
                "Jantan : 5 kg - 8 kg\n" +
                        "Betina : 4 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed15,
                "Cornish Rex",
                "Rex cornish adalah salah satu ras kucing domestik berbulu pendek, bahkan sangat pendek, dan keriting, yang terjadi akibat adanya mutasi genetik pada nenek moyang mereka, yaitu seekor anak kucing yang terlahir sekitar tahun 1950-an di sebuah area pertanian di Cornwall, Inggris. Maka dari itu kucing jenis ini diberi nama \"\"cornish\"\" yang merupakan serapan dari kata \"\"cornwall\"\". Kucing ini cenderung berotot dan memiliki bentuk yang ramping serta berkaki panjang. Mereka seolah lebih berat dari kelihatannya. Kucing ini memiliki kepala melancip, tengkorak yang rata, telinganya lebar yang tinggi. Bagian yang paling membedakan jenis ini adalah lapisan rambutnya yang ikal, namun lembut ketika disentuh. Lapisan ini tidak memiliki helai yang panjang, namun tetap terlihat bergelombang dan beriak. Kumisnya pun ikal, sebagaimana bagian lain dari lapisan rambut luarnya. Jenis kucing Cornish Rex memiliki aroma tersendiri, yang sering dideskripsikan oleh pemilikya, “seperti keju”. Warna maupun motifnya pun beragam.\n",
                "Rp 11 jt - Rp 18 jt",
                "2.5 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed16,
                "Cymric",
                "Badan jenis kucing Cymric kokoh dan cenderung gemuk, mirip dengan kucing jenis British Shorthair, dengan mata besar dan telinga yang cukup berjarak. Jenis ini sangat mudah dikenali dari tidak tampaknya ekor. Kaki belakangnya lebih panjang dari kaki depan. Rambut kucing Cymric ini tebal dan seolah befungsi sebagai pelapis tubuhnya, membuat tampilan keseluruhannya jadi membulat. Perilaku melompat-lompat seperti kelinci kerap ditemukan pada kucing Cymric, diakibatkan oleh kelainan bentuk tulang punggungnya, mirip dengan spinabifida, yang sering ditandai dengan tidak adanya ekor. Kucing Cymric dapat memiliki banyak warna dan motif, namun tidak seperti jenis kucing Siamese.",
                "Rp 11 jt - Rp 16,8 jt",
                "3.5 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed17,
                "Devon Rex",
                "Kesan pertama kucing Rex Devon adalah wajah terlihat nakal dan rambut yang berkerut. Pipinya lebar, mata dan telinganya sangat besar, yang posisinya agak ke bawah. Rambutnya pendek, berfungsi sebagai pelindung yang bergelombang, dan kebanyakan adalah lapisan bagian dalam. Kucing Devon Rex memiliki karakteristik selayaknya Rex ‘Marcel wave’, rambutnya seolah membentuk riak dan gelombang, terutama di punggung. Janggut dan alisnya pun bergelombang. Jenis Devon Rex ini memiliki berbagai warna dan corak.",
                "Rp 16 jt - Rp 26 jt",
                "2.5 kg - 4 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed18,
                "Domestic Long Hair",
                "Kucing domestik berbulu panjang adalah kucing domestik ras campuran yang memiliki bulu berukuran panjang. Kucing ini bukan merupakan ras kucing. Kucing ini bukanlah kucing ras kucing bulu panjang britania, kucing bulu panjang amerika, atau ras lainnya dengan nama \"bulu panjang\", yang jenisnya diakui sebagai ras kucing oleh berbagai organisasi pendaftaran kucing. Domestik berbulu panjang adalah kucing yang paling populer kedua di Amerika Serikat setelah kucing domestik berbulu pendek; satu dari sepuluh kucing dari 90 juta kucing domestik berbulu panjang di Amerika Serikat. Istilah generik lainnya dari kucing ini adalah kucing rumah berbulu panjang dan di Britania Raya, mogi berbulu panjang.",
                "Rp 1.5 jt - Rp 15 jt",
                "5 - 10 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed19,
                "Domestic Medium Hair",
                "Kucing domestik berbulu medium adalah kucing lucu yang ditemukan di rumah tangga di seluruh dunia. Kucing ini adalah sepupu dekat dengan kucing domestik berbulu pendek dan kucing domestik berbulu panjang. Tetapi ia memiliki gen unik yang membuatnya memiliki bulu sedang yang mewah. Menariknya, kucing ini bisa menjadi keturunan dari kucing domestik berbulu pendek atau domestik berbulu panjang, kebalikannya juga. Secara teknis tidak dianggap sebagai ras, rambut medium domestik dengan penuh kasih dianggap sebagai \"anjing kampung\" di dunia kucing. Ciri khas mereka yang berukuran sedang, mantel ganda kurang umum daripada bulu pendek, dan kucing berbulu halus ini cenderung sedikit lebih besar dan lebih berat daripada sepupu berbulu pendek mereka. Karena keragaman genetiknya, kucing berbulu sedang memiliki kepribadian dan ciri fisik yang beragam. Beberapa suka dipeluk sementara yang lain lebih suka kemandiriannya, sehingga mudah menemukan kucing yang sangat cocok untuk keluarga mana pun.",
                "Rp 1.5 jt - 12 jt",
                "5 - 10 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed20,
                "Egyptian Mau",
                "Ras kucing Mau Mesir adalah kucing yang elegan dan tampak atletis yang ditandai dengan pola bintik-bintik acak. Kucing Mau konon punya ekspresi 'kekhawatiran' yang unik. Mau adalah kata Mesir untuk kucing dan memiliki kemiripan yang mencolok dengan patung kucing tutul yang digambarkan dalam seni Mesir Kuno. Ras ini berasal dari kucing tutul yang ditemukan di Kairo yang konon dibawa ke Roma pada tahun 1953 oleh seorang putri Rusia. Dia membiakkan sejumlah kucing tutul di Italia dan kemudian pindah ke Amerika Utara di mana pembiak mengembangkan Mau modern. Tanda-tanda khas di kepalanya dikatakan menyerupai kumbang scarab yang disakralkan oleh orang Mesir Kuno. Kucing Mau baru diimpor ke Inggris pada tahun 1998. Kucing Mau Mesir adalah jenis kucing yang penyayang dan suka bermain. Dikatakan sangat pintar dan menikmati belajar trik dan berjalan di atas petunjuk. Menikmati keributan dan perhatian, ras ini menjadikannya kucing keluarga yang ideal. Saat mereka aktif, kucing Mau membutuhkan beberapa rangsangan seperti mainan atau teman untuk bermain, terutama jika mereka akan dibiarkan sendiri dalam waktu lama.",
                "Rp 10 jt - Rp 30 jt",
                "3.6 kg - 5.5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed21,
                "Exotic Shorthair",
                "Kucing bulu pendek eksotis berperawakan sedang, dan badannya kekar. Kepalanya bundar dan lebar dengan sepasang telinga kecil lebar dan wajah pendek. Matanya besar dan bulat dengan warna yang terang. Kakinya pendek, tebal, dan kokoh dengan telapak kaki yang bundar dan berumbai. Ekornya pendek dan berambut lebat. Rambut pendek, tebal, dan lebat dengan rambut lapisan bawah yang padat dan lembut. Kucing ini memiliki semua pola dan warna rambut yang dimiliki oleh kucing Persia dan ada pula pola totol pada kucing betina. Kucing  bulu pendek eksotis memiliki perangai lembut yang sama dengan sepupu jauhnya yang berambut panjang, dan tidak seberisik ras kucing lain yang berambut pendek. Kucing - kucing eksotis bisa hidup senang sendirian di rumah dalam kondisi yang tentram. Kucing  bulu pendek eksotis pada dasarnya adalah ras Persia berambut pendek, tetapi sering kali dimasukkan ke dalam kategori rambut panjang di ajang pameran atau kompetisi kucing. Di Amerika, pengembangbiakkan American Shorthair dengan Persia berambut panjang dilakukan untuk menghasilkan kucing yang mirip dengan kucing Persia kecuali rambut. Di Britania, British Shorthair dikawin-silang dengan Persia berambut panjang dengan cara yang sama. Rambut, yang panjangnya sedang, lebih mudah dirawat dan tetap memiliki variasi warna dan pola yang luas. Kucingini pertama kali dipopulerkan tahun 1960-an dan segera menjadi ras yang populer.",
                "Rp 16,8 jt - Rp 31 jt",
                "5.5 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed22,
                "Havana",
                "Kucing havana adalah salah satu ras kucing yang berasal dari Thailand. Ras ini pertama kali dikembangkan di Inggris. Havana dapat hidup dengan mencapai umur 12 tahun. Havana adalah kucing berbadan sedang, ramping, dan anggun dengan berat badan mencapai sekitar 2.5–6 kg, dan panjang mencapai sekitar 50–70 cm. Bulunya pendek, dengan warna bulu hanya memiliki coklat saja. Bulunya lembut, halus, dan terlihat mengkilap. Matanya berbentuk oval dan berwarma hijau cerah. Telinganya besar, lebar, dan tinggi, serta ekornya panjang dan lurus.",
                "Rp 8 jt - Rp 16 jt",
                "4 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed23,
                "Himalayan",
                "Kucing Himalaya adalah salah satu ras kucing domestik yang merupakan hasil persilangan antara kucing persia dengan kucing siam. Persilangan tersebut bertujuan untuk mendapatkan ras kucing dengan tipe badan yang sama seperti kucing persia, tetapi dengan pola warna seperti kucing siam. Kucing himalaya sangat identik dengan mata yang berwarna biru dan pola warna poin. Kucing ini merupakan kucing yang manis dan pemarah. Dia penyayang tapi selektif. Meskipun dia suka berbaring di pangkuan Anda dan menjadi hewan peliharaan, dia mungkin pendiam di sekitar tamu. Lingkungan yang tenang dan tenang dengan sedikit perubahan dari hari ke hari adalah yang terbaik untuk Himmie. Kucing ini bertubuh gemuk, besar, dan bulat dengan kaki yang pendek seperti kucing persia. Hal tersebut membuat mereka sulit untuk melompat. Namun, beberapa ras kucing ini ada yang memiliki tubuh seperti kucing siam, yaitu tubuh yang langsing, ramping, dan anggun.",
                "Rp 1 jt - Rp 35 jt",
                "3 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed24,
                "Japanese Bobtail",
                "Kucing ekor bundel jepang memiliki bentuk elegan dan otot-otot yang terdefinisi dengan jelas. Kepalanya seolah dipahat dengan baik, telinga besar menghadap ke atas, mata besar membulat namun dengan sudut yang miring. Ekornya ketika normal hanya mencapai panjang 5-8 cm. Ketika kucing ini sedang dalam kondisi rileks, ekornya menghadap ke atas. Rambut pada ekornya lebih tebal dan panjang dibanding bagian lain tubuh, dan tumbuh memanjang ke segala arah mengakibatkan bentuknya terlihat seperti pompom atau ekor kelinci. Kucing ekor bundel dapat berambut panjang maupun pendek. Rambutnya lembut dan halus dengan berbagai macam warna. Orang-orang di Jepang lebih menyukai kucing ekor bundel dengan kombinasi tiga warna, yakni campuran tortie dan putih (dikenal dengan istilah Mi-ke), yang melambangkan keberuntungan. Campuran warna ini biasanya ditemukan hanya di betina. Kucing ini ramah dan cerdas. Ras ini dikatakan memiliki sifat manis dan bergaul dengan sebagian besar hewan lain dan suka berteman dengan manusia. Beberapa dapat mempelajari trik dan perlu tetap terhibur dan terstimulasi. Ini adalah jenis yang cukup banyak bicara. Ciri khas kucing ekor bundel jepang adalah ekornya. Saat kucing dalam keadaan santai dan alami, ekornya terlihat seperti kelinci, tetapi dapat dibentangkan hingga panjangnya sekitar 10 hingga 12 cm. Di Jepang, Ras ini dapat ditelusuri kembali ke abad ke-8 tetapi tidak terlihat di luar Jepang sampai tahun 1960-an ketika kucing ekor bundel jepang diimpor ke Amerika. Kucing ini telah memiliki pengikut di Amerika dan sekarang menjadi ras yang diakui dan ditampilkan secara luas di AS. Tidak banyak di Inggris.",
                "Rp 7 jt - Rp 10 jt",
                "Jantan : 4 kg - 6 kg\n" +
                        "Betina : 3.5 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed25,
                "Korat",
                "Kucing Korat memiliki lapisan rambut yang pendek, halus, mengkilat, dan seolah menempel erat pada tubuhnya. Kucing ini hanya memiliki satu jenis warna yakni kebiruan dengan ujung perak. Kulit hidung, bibir dan cakarnya berwarna ungu terang ataupun paduan abu-abu dan biru gelap. Tampilan kucing ini mirip dengan kucing jenis Russian Blue, namun bedanya hanya memiliki satu jenis lapisan rambut. Secara keseluruhan bentuknya pun lebih membulat. Matanya berwarna hijau peridot, dan bukannya zamrud. Kepalanya berbentuk seperti hati dengan telinga lebar, mata yang besar dan ekspresi yang sangat berhati-hati. Tubuhnya kuat dan berotot, tidak memanjang seperti jenis kucing Siam, dan tidak kekar seperti kucing British Shorthair. Kucing Korat memiliki sifat pendiam, manis, tetapi sangat cerdas dan suka bermain. Mereka merupakan hewan peliharaan yang ideal karena dapat menjadi sahabat bagi manusia. Ras kucing Korat dinamai menurut sebuah provinsi di Thailand. Di Thailand, di mana jenis ini sering dikenal sebagai 'Si-Sawat' (artinya keberuntungan) - sepasang kucing Korat adalah hadiah tradisional untuk pengantin wanita. Seekor kucing Korat mungkin telah diikutsertakan dalam pertunjukan kucing Inggris pada abad ke-19 sebagai kucing Siam Biru. Mereka pertama kali muncul di Amerika pada 1950-an dan tiba di Inggris Raya dari sana pada 1972. Mereka adalah salah satu dari sedikit ras yang hanya tersedia dalam satu warna, dalam hal ini biru keperakan.",
                "Rp 9 jt - Rp 12 jt",
                "3.5 kg - 6.5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed26,
                "LaPerm",
                "Kucing LaPerm awalnya merupakan kucing lumbung berbulu keriting di Oregon, Amerika Serikat, ditemukan pada tahun 1982. Kucing-kucing ini berkeliaran bebas di sekitar area peternakan dan kawin silang dengan kucing-kucing lain di sekitarnya. Selama sepuluh tahun berikutnya, pemilik peternakan menyadari bahwa dalam kelahiran anak kucing secara berurutan, sering kali terdapat kucing berbulu keriting. Kemudian, secara selektif mengembangbiakkan kucing-kucing berbulu keriting tersebut dan membawanya ke pertunjukan kucing. Pemilik peternakan memilih nama \"La Perm\" untuk menggambarkan bulu kucing yang khas. Kucing ini mampu hidup sekitar 13-15 tahun dengan tubuh 4-7 kg. Setiap harinya kucing LaPerm mampu menghabiskan 70 Kcals makanan per kg berat badannya.",
                "Rp 3 jt - Rp 9 jt",
                "2.5 kg - 4.5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed27,
                "Maine Coon",
                "Kucing Maine Coon berasal dari Amerika dengan nenek moyang yang berasal dari kucing berambut panjang yang dikawinkan dengan kucing shorthair hal tersebut menjadikannya populer pada pertunjukan yang sering digelar pada 1860-an. Kucing Maine Coon merupakan jenis kucing besar yang memiliki tubuh berotot dan kaki yang kuat. Dengan moncong berbentuk kotak, telinga lebar, dan tinggi serta bulu panjang dan tebal, terdiri dari lapisan dalam yang lembut dan lapisan luar yang mengkilap serta tahan air. Rambut di bagian kepala, leher, dan bahu lebih pendek, sedangkan rambut di bagian punggung, pinggiran, dan ekor lebih panjang. Bulu di perut dan bagian bawahnya sangat penuh dan terlihat acak-acakan. Ada pula rambut yang tumbuh di sekitar leher, dimulai dari bawah telinga, dan lebih panjang pada jantan daripada betina. Bulu di ekor kucing ini panjang dan lebat. Ujung telinganya memiliki bentuk lancip, dan cakarnya memiliki rumbai seperti sepatu salju. Maine Coon memiliki lebih dari 30 variasi warna yang berbeda. Warna mata kucing ini bervariasi mulai dari hijau, emas, atau cokelat seperti perunggu. Ada hal yang penting diketahui sebelum kalian mengadopsi kucing ini, yaitu terkait prevalensi tinggi terhadap kondisi jantung(kardiomiopati hipertrofik) bahkan memiliki prevalensi tinggi displasia pinggul, oleh sebab itu konsultasikan dengan pembiak sebelum anda meminangnya.",
                "Rp 8 jt - Rp 30 jt",
                "Jantan : 5 kg - 9 kg\n" +
                        "Betina : 3 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed28,
                "Manx",
                "Kucing Manx berasal dari Inggris, dengan tanpa ekor, usul punya usul ekor kucing ini dulunya dipercayai putus karena Nabi Nuh yang terlalu cepat menutup pintu bahtera yang mengakibatkan ekornya terputus. Namun, berdasarkan penelitian ekor kucing ini tidak ada karena hasil mutasi genetik yang disebabkan oleh perkawinan sedarah antara kucing British Shorthair. Sekilas kucing ini yang sangat mirip dengan British Shorthair, kecuali pada bagian ekornya. Tubuhnya terlihat kompak dan kuat, dengan dada yang lebar dan punggung yang pendek. Bagian belakang kucing ini berbentuk bulat dan sedikit lebih tinggi dari bahunya. Kaki-kakinya pendek dan kuat, dengan kaki belakang yang sedikit lebih pendek daripada kaki depannya. Ada dua jenis bulu yang ditemukan pada kucing Manx, yaitu lapisan dalam yang tebal dan pendek, serta lapisan luar yang sedikit lebih pendek dan tipis. Dengan ekor yang cacat genetik sejak lahir menjadi pertimbangan akan kesehatan kucing ini yang mungkin rentan terhadap radang sendir sejak usia dini serta saluran anus yang mungkin menyempit dan bahkan penyumbatan anus. ",
                "Rp 11 jt - Rp 21 jt",
                "4 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed29,
                "Munchkin",
                "Kucing Munchkin berasal dari Amerika pada tahun 1983 yang akrab disebut The Wizard of Oz. Kucing ini memiliki tampilan yang manis dan ramah serta ukuran tubuh yang sedang dengan kaki yang sangat pendek namun tulang belakang yang panjang membuat tampilannya sangat digemari setiap orang. Bulu kucing ini bisa bertipe panjang maupun pendek dengan lapisan rambut yang tebal dan mewah sanggup menghadapi berbagai jenis cuaca. Kaki pendek yang dimiliki kucing ini merupakan kelainan bentuk yang berkaitan dengan masalah tulang belakang.",
                "Rp 7 jt - Rp 20 jt",
                "Jantan : 3 kg - 4 kg\n" +
                        "Betina : 2 kg - 3.6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed30,
                "Nebelung",
                "Kucing Nebelung merupakan kucing yang sangat cantik namun kekar. Memiliki kemiripan dengan Kucing Russian Blue yang memiliki tubuh tegap, lentur, dan berotot, serta kepala yang rapi. Hal yang membedakan hanya pada bulu Nebelung yang agak panjang dan telinga berumbai. Kucing ini umumnya mampu hidup 15-18 tahun dengan berat 3.5-6.5 kg dan warna mata abu-abu pekat. Dengan kepribadiannya yang pemalu dan menyendiri terhadap orang asing. Serta sensitif terhadap kebisingan dan hiruk pikuk namun kucing ini sangat menyukai kebersamaan dengan manusia dan bergaul baik dengan anjing serta anak-anak. ",
                "Rp 4 jt - Rp 14 jt",
                "Jantan : 5 kg - 6.5 kg\n" +
                        "Betina : 3.5 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed31,
                "Norwegian Forest ",
                "Kucing Norwegian Forest berasal dari kucing shorthair yang dibawa bangsa viking dari Inggirs Raya dan longhair yang dibawa oleh Tentara salib ke skandinavia yang dikawinkan dengan kucing lokal. Ras ini ditemukan sekitar 1930 di Skandinavia tepatnya di Norwegia. Kucing Norwegian Forest memiliki karakteristik badan panjang, berotot dan kuat serta struktur tulang yang kokoh. Kucing ini memiliki kaki panjang dan ekor lebat. Kucing ini memiliki kepala panjang dan berbentuk segitiga dengan profil lurus, telinga tinggi dengan ujung lancip. Rambutnya mengkilap dan tahan air, menutupinya seperti wol mulai dari leher, depan, hingga rumbai antar jari kaki. Kucing ras ini memiliki kemampuan adaptasi yang baik yang membuatnya sangat baik untuk berkeliaran dan berburu di luar. Kucing ini memiliki catatan kesehatan yang baik tanpa ada warisan penyakit yang krusial.",
                "Rp 11 jt - Rp 21 jt",
                "Jantan : 4.5 kg - 9 kg\n" +
                        "Betina : 3.5 kg - 8 kg"
            )
        )


        breedList.add(
            BreedCat(
                R.drawable.img_breed32,
                "Ocicat",
                "Kucing Osicat berasal dari Amerika dari perkawinan eksperimental tahun 1960-an di Amerika. Kucing Osicat merupakan kucing yang berukuran sedang hingga besar dengan motif rambut jelas,  Dadanya dalam dan lebar dengan kaki yang kuat dan berotot. Kepalanya lancip, dengan rahang yang cenderung kotak. Telinganya besar dan lebar namun melancip, membuat kucing ini terlihat liar. Rambut Osicat pendek dan licin dengan tekstur seperti kain satin. Motif totolnya tersebar di bagian pinggir, bahu dan belakang, memanjang ke kaki hingga perut. Dahinya dengan jelas terlihat membentuk huruf M. Ekornya menunjukkan motif totol-totol dan lingkaran warna gelap, dan ujungnya berwarna lebih gelap. Kucing ras ini tidak memiliki kelainan yang krusial sampai saat ini.",
                "Rp 12jt - Rp 18 jt",
                "Jantan: 4.5 - 5.4 kg\n" +
                        "Betina: 3.1 - 5.4 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed33,
                "Oriental Long Hair",
                "Kucing Oriental Rambut Panjang berasal dari Inggris dengan asal usul awal dari Turki pada abad ke-19 dibawa ke Eropa Barat. Kucing ras ini tercipta kembali oleh persilangan kucing Abyssinian gen rambut panjang dengan kucing Siam. Adapun karakteristik kucing ini yaitu memiliki tubuh yang panjang, lentur dan elegan, persis seperti tubuh kucing Siam dengan kepala dan telinga berbentuk segitiga yang lancip. rambutnya panjang dan seperti sutra namun berbeda dengan kucing Persia, rambut bagian dalam yang seperti wol dan rambut luarnya cenderung jatuh mengikuti bentuk tubuh sehingga kerap digolongkan sebagai semi-longhair. Ekornya membentuk jambul yang indah. Rentang kemungkinan warna rambutnya dimulai dari seluruh warna solid hingga motif asap dengan berbagai karakter. Semua Oriental Longhair memiliki mata hijau dengan bentuk seperti almond, kecuali jenis putih, yang bisa juga bermata biru, atau bahkan dua mata dengan warna berbeda satu sama lain. ",
                "Rp 11 jt - Rp 22 jt",
                "Jantan: 3.6 - 5.4 kg \n" +
                        "Betina: 2.7 - 3.6 kg "
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed34,
                "Oriental Short Hair",
                "Kucing Oriental Short Hair berasal dari Amerika dan Inggris, ukuran tubuhnya sedang namun terasa lebih berat dari kelihatannya. Sekilas tubuhnya terlihat seperti Siamese yang panjang, kurus, dengan ekor seperti cambuk dan kaki yang jenjang. Kepalanya berbentuk meyerupai segitiga dengan ujung yang lancip, bentuk kepalanya memanjang, disertai sepasang kuping yang lebar. Kucing ini memiliki rambut sangat pendek, dengan tekstur yang halus dan mengkilat. ",
                "Rp 8 jt - Rp 20 jt",
                "2 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed35,
                "Persian",
                "Persian Longhair berasal dari Persia dan Turki. Kucing ras ini berukuran sedang dengan ukuran kepala yang besar bila dibandingkan dengan ukuran tubuhnya. Kepalanya bulat dan lebar, dengan telinga yang kecil dan wajah yang terbuka. Kakinya pendek, tebal dan kuat dengan cakaran besar namun berujung lancip. Ekornya pendek dan rambutnya lebat. Rambut luarnya panjang, tebal dan terlihat mewah dengan rambut bagian dalam yang lebat. Tercatat bahwa kucing ras ini diperkirakan hidup sekitar 10-12 tahun namun memiliki sejumlah potensi masalah kesehatan yang disebabkan oleh bentuk rahang yang mampu menyebabkan penyakit gigi, dan masalah makan dan minum. Lubang hidung yang kecil dan langit-langit lunak terlalu panjang mengakibatkan masalah pernapasan yang parah. Serta saluran air mata yang tidak sesuai mengakibatkan wajah iritasi oleh karena air mata yang mengalir terus menerus. Kucing Persia juga dikenal memiliki gen pembawa gagal ginjal melalui perkembangan kista di ginjal.",
                "Rp 300 k - Rp 10 jt",
                "3 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed36,
                "Pixiebob",
                "Pixiebob merupakan kucing besar dengan tubuh yang kekar dan kuat. Menyerupai kucing hutan Amerika Utara dalam bentuk mini, Pixiebob memiliki tubuh berotot, wajah berbentuk buah pir dengan telinga berumbai, dan bulu berbulu yang menonjol dari tubuhnya. Pixiebob umumnya memiliki ekor yang pendek, bahkan tidak memiliki ekor, namun hal ini bervariasi pada setiap kucing dan mereka dapat memiliki ekor dengan panjang berapapun. Pixiebob biasanya berbulu pendek, namun ada juga anak kucing yang berbulu panjang, mereka juga merupakan salah satu dari sedikit ras yang memperbolehkan polydactyl (jari kaki ekstra) dalam standar ras - maksimal tujuh jari kaki diperbolehkan pada setiap kaki. Kucin ini mampu hidup 13-15 tahun dengan berat 3.6-7.7 kg",
                "Rp 8 jt - Rp 21 jt",
                "Jantan : 5 kg - 11 kg\n" +
                        "Betina : 4 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed37,
                "Ragamuffin",
                "Kucing ini pada awalnya dikenal sebagai ras Ragdoll. Namun, karena dalam asosisasi pendiri ras beberapa anggota memisahkan diri dan menciptakan ras baru yaitu Ragamuffin. RagaMuffin adalah kucing yang memiliki tubuh berbentuk persegi panjang dengan dada lebar, bahu lebar, dan otot yang cukup kuat di bagian belakang. Kepalanya berbentuk segitiga yang sedikit membulat, dengan rahang yang bulat dan pipi yang penuh. Telinganya sedang, sedikit miring ke depan, dan berbulu. Matanya besar, berbentuk kenari, dan menambah kesan \"manis\" pada wajahnya. Kaki-kakinya berotot dengan cakar yang besar dan bulat. Ekor panjang dengan bulu yang penuh, dan bulunya sendiri berukuran medium hingga medium-panjang, lembut, padat, dan berkilau.",
                "Rp 8 jt - Rp 18 jt",
                "3 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed38,
                "Ragdoll",
                "Kucing Ragdoll berasal dari Amerika tepatnya California tahun 1960-an yang merupakan hasil perkawinan antara ratu kucing Persia Putih(betina) dan Birman atau Birman-type Tom. Karena sifatnya yang tenang dan rileks ketika diangkat seperti boneka ragdoll. Kucing ras ini memiliki tubuh besar dan kuat. Kucing ini dikenal sebagai pemalas dari kesannya yang santai. Kucing ras ini memiliki mata biru tua yang besar. Tubuhnya panjang dan berotot dengan dada lebar, leher pendek dan kaki yang kokoh. Cakarnya bulat besar dan berumbai, sedangkan ekornya panjang dan lebat. Rambut kucing Ragdoll halus dan padat, memiliki ukuran sedang hingga panjang. Terdapat tiga pola rambut yang berbeda pada ras kucing ini. Ragdoll memiliki hubungan penyakit jantung bawaan yang memungkinkan gagal jantung usia dini",
                "Rp 1,5 jt - Rp 28 jt",
                "3 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed39,
                "Russian Blue",
                "Russian Blue adalah kucing berukuran sedang yang menarik dengan tubuh yang agak 'asing' ('asing' dalam istilah kucing berarti 'seperti kucing Siam'), panjang dan luwes serta sangat berotot, namun tetap anggun. Kepalanya berbentuk baji, tetapi tulang pipi yang lebar membuat Russian Blue memiliki wajah yang terbuka dan tersenyum, dibingkai dengan telinga yang besar dan melebar. Kaki dan panjang dan ramping dengan cakar yang rapi, kecil, dan berbentuk oval. Russian Blue terkenal dengan bulunya yang lebat, tebal dan mewah. Kucing ras ini memiliki umur sekitar 10 sampai 15 tahun dengan berat 3 sampai 7 kilogram. Russian Blue adalah teman yang menarik dan elegan oleh karena kepribadiannya yang menyendiri. Hal tersebut menyebabkan kucing ini lebih sering mengamati dari kejauhan dan mengevaluasi kelayakan manusia baru mau berteman. ",
                "Rp 20 jt - Rp 33 jt",
                "Jantan : 4.5 kg - 5.5 kg\n" +
                        "Betina : 3 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed40,
                "Scottish Fold",
                "Kucing yang identik dengan lekukan karena desain tubuhnya yang cenderung membulat serta kepala dengan mata yang besar serta telinga yang kecil terlipat rapi kedepan sehingga lubang telinga tertutup membuat kucing ini terlihat manis dengan bulu pendek lebat nan mevvah. Kucing ras ini mampu hidup 11 sampai 15 tahun dengan beratnya yang ringan yaitu mulai dari 2,5 hingga 6 kilogram.",
                "Rp 7 jt - Rp 30 jt",
                "3 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed41,
                "Selkirk Rex",
                "Selkirk Rex dikenal berasal dari Amerika Serikat pada tahun 1987. Kucing ras ini berukuran sedang hingga besar dengan tulang yang padat yang menjadikannya berat dan terlihat kuat. Tubuh ras kucing satu ini terlihat tembem dengan kepala bulat dan juga moncong yang pendek. Namun begitu, Selkirk Rex bisa saja berambut pendek ataupun panjang. Kemudian, rambut tersebut dapat hadir dalam tiga jenis--berdiri, jabrik, dan juga pendek--yang bertekstur lembut dan bergelombang. Lengkungan pada rambutnya pun berbeda-beda, tergantung umur, jenis kelamin, cuaca, dan juga musim. Pada kucing ras Selkirk Rex berambut panjang, lengkungan rambut tersebut menjauh dari buntutnya dan juga melebat. Lalu, rambut kasarnya juga panjang dan seolah membentuk wajah mereka. Rambut ras kucing Selkirk Rex hadir dalam berbagai warna dan juga pola. Kucing ini memiliki kepribadian yang waspada dan aktif dengan watak manis dan menawan. Dari segi kesehatannya, kucing ini memiliki kasus kerontokan rambut, ginjal polikistik.",
                "Rp 8 jt - Rp 21 jt",
                "2 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed42,
                "Siamese",
                "Populer di Inggris namun aslinya kucing Siam berasal dari Thailand. Kucing Siam merupakan ras berukuran sedang dengan tubuh yang panjang dan lentur, yang terlihat anggun dan elegan, namun tetap berotot. Tubuh mereka cukup seimbang dan atletik, dengan lengan yang ramping serta cakar yang kecil dan lonjong. Ketika ditatap lurus, kepala mereka menyerupai segitiga yang meruncing dalam garis lurus mengarah ke moncongnya yang halus. Telinganya besar dan seolah berduri serta seolah diatur sedemikian rupa sehingga mengikuti garis segitiga. Matanya berbentuk sipit dan miring ke arah hidung. Ekornya panjang dan meruncing serta bebas dari kekusutan. Rambutnya pun pendek, halus dan tumbuh berdekatan serta memiliki warna tubuh utama pucat dengan titik-titik berwarna lebih gelap. Titik-titik berwarna ini hanya meluas ke area topeng wajah, telinga, kaki dan ekor yang merupakan bagian tubuh yang lebih dingin. Kemudian, titik-titik ini muncul dalam berbagai macam warna. Namun begitu, warna mata di semua kucing akan berwarna biru yang cerah. Kucing ini dikenal berumur panjang, namun dalam beberapa kasus ras ini memiliki resiko kanker jenis tertentu, batuk kronis hingga muntah lebih banyak dari ras lain.",
                "Rp 500.000 - Rp 16 jt",
                "4 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed43,
                "Siberian",
                "Kucing Siberia berasal dari Rusia dan dikateogirkan sebagai kucing nasional Rusia. Kucing ini termasuk ras kucing besar yang berotot serta dengan bentuk batang tubuh layaknya tong. Rambutnya panjang dan padat, kepala berbentuk seperti segitiga dengan kontur bulat, serta matanya sangat ekspresif. Penampilan fisik mereka secara keselurahan menunjukkan kekuatan. Rambut yang lebih panjang pada triple coat berwarna pucat di dekat kulit dan menggelap di ujung luar. Kondisi ini menjadikan rambut mereka berkilau saat bergerak. Walaupun garis hitam dan rambut coklat merupakan varian yang paling umum. Kucing Siberia dikenal sebagai kucing dengan jangka hidup 11 hingga 15 tahun dengan kepribadian yang setia, mudah bergaul dan percaya diri, hingga keanehan yaitu gemar memainkan air, seperti di mangkuk, maupun air menetes seperti dari wastafel. Dari catatan kesehatannya kucing ras ini berisiko mengalami penyakit jantung bernama hypertrophic cardiomyopathy.",
                "Rp 16 jt - Rp 56 jt",
                "Jantan : 6 kg - 9 kg\n" +
                        "Betina : 4 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed44,
                "Singapura",
                "Sesuai dengan namanya kucing ras ini berasal dari Singapura dikenal sebagai kucing saluran pembuangan karena gaya hidupnya liar di got maupun saluran pembuangan. Pada tahun 1974, kucing ini dianggap sebagai hama karna populasinya sehingga dilakukan pemusnahan yang menyisakan 3 kucing yang kemudian dibawa ke Amerika untuk dikembangkan kembali sehingga popularitasnya meningkat. Dari segi tampilan kucing ras ini mirip dengan kucing Abyssinian. Kepalanya berbentuk bulat dengan kumis yang tertata rapi dan moncong pendek yang lebar. Telinganya besar dan berbentuk cekung yang dalam. Mata mereka besar dan berbentuk layaknya kacang almond yang sedikit miring  dan terbuka lebar. Meskipun kucing ini tergolong kecil, tubuhnya termasuk kekar dan berotot. Kemudian, ekornya terlihat ramping dan memiliki ujung yang tumpul. Kucing Singapura memiliki rambut pendek dan halus yang hadir dalam warna garis-garis hitam, cokelat, atau krim. Namun, seluruh jenis kucing memiliki warna rambut lebih gelap pada bagian punggung, buntut, dan juga kaki-kakinya. Kemudian, perut dan dadanya secara kompak memiliki warna krim, apa pun warna utama kucing tersebut. Akan tetapi, warna mata bisa saja berbeda-beda, ada yang cokelat hazel, hijau, dan kuning, semuanya dengan garis luar berwarna hitam.",
                "Rp 18,5 jt - Rp 47 jt",
                "Jantan : 2.7 kg - 3.5 kg\n" +
                        "Betina : 1.8 kg - 2.5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed45,
                "Snowshoe",
                "Kucing Snowshoe berkembang pada awal tahun 1960an, seorang peternak Amerika mengawinkan American Shorthair dengan Siamese. Kucing ras ini terkenal tampan dan mirip seperti American Shorthair dengan keanggungan dan garis luwes dari Siamese. Kepalanya bulat, dengan mata sipit yang besar dan telinga yang lebar. Tubuhnya panjang dan atletis, Snowshoe memiliki cakar yang rapi, berbentuk oval, dan ekor yang cukup panjang. Secara keseluruhan, Snowshoe adalah kucing berukuran sedang yang seimbang secara fisik, dan dibangun untuk berlari dan melompat. Kucing ini memiliki rentang hidup 14 hingga 19 tahun dan dengan bobot 4 sampai 5,5 kilogram. ",
                "Rp 8 jt - Rp 18 jt",
                "3 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed46,
                "Somali",
                "Kucing Somalia bertubuh sedang; kuat, lentur dan berotot. Kepala membentuk irisan sedang dengan kontur lembut dan telinga terpisah lebar dan berumbai. Mata berbentuk almond, terpisah dengan baik dan sering berwarna kuning, cokelat atau hijau - semakin dalam bayangan semakin baik. Kakinya panjang dan memiliki cakar berbentuk oval. Rambutnya berukuran sedang, lembut, padat. Kucing Somalia dewasa memiliki kulit yang kasar dan bokong penuh, tetapi ini tidak akan dimiliki pada anak kucing. Pada setiap rambut harus ada setidaknya tiga tanda yang memberikan enam bagian warna yang kontras dari pangkal hingga ujung. Kucing Somalia hadir dalam 28 pilihan warna. 'Biasa' berwarna cokelat keemasan dan memiliki bercak warna hitam. Kucing Somalia sangat cerdas. Jenis Somalia baik hati, suka bermain dan menikmati permainan. Kucing Somalia mungkin lebih pemalu dan lebih mandiri daripada saudaranya Abyssinian. Mereka menikmati kebersamaan dengan manusia. Mereka aktif dan suka aktivitas di luar ruangan. Kucing Somalia adalah versi berambut panjang dari Abyssinian. Gen rambut panjang diperkenalkan ke ras Abyssinian pada awal 1900-an tetapi varietas rambut panjang tidak dibiakkan secara khusus sampai tahun 1960-an. Pengenalan asli gen berambut panjang terjadi di Inggris dan Abyssinians yang membawa gen resesif diekspor ke Eropa dan Amerika. Pembiakan sistematis kucing Somalia dimulai di Amerika. Somalia segera menjadi populer di bagian lain, khususnya Australia di mana mereka dibiakkan hampir dengan mengesampingkan Abyssinian.",
                "Rp 14 jt - Rp 21 jt",
                "2.7 kg - 5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed47,
                "Sphynx",
                "Kucing sphynx adalah salah satu ras kucing yang memiliki bulu sangat pendek dan sedikit sekali. Jika dilihat sekilas, kucing sphynx tampak tidak memiliki bulu sama sekali, tetapi jika diamati lebih saksama tubuh kucing ini ditumbuhi rambut halus di beberapa bagian, seperti telinga, kaki, ekor, dan didekat organ kelamin. Kucing sphynx berasal dari Kanada. Namun, penyebarannya dapat ditemukan di beberapa tempat seperti Kanada, Prancis, Maroko, Meksiko, Rusia, Australia dan Amerika Serikat. Kucing ras sphynx merupakan salah satu jenis kucing hasil rekayasa genetik. Kucing ini tidak memiliki kumis atau bulu mata. Kepalanya menyerupai kucing Rex Devon. Matanya dalam dan berbentuk lemon. Tubuhnya bertulang halus tetapi berotot dan memiliki dada laras. Kakinya panjang dan ramping dan memiliki penampilan berkaki busur yang disebabkan oleh dada yang rata. Ekornya panjang dan meruncing dan sulit disentuh. Saat menyentuh mereka terasa hangat dan lembut untuk disentuh dan sekilas seperti menyentuh bahan \"suede\". Ciri khas pada kulitnya berkerut di bagian kepala, tubuh dan kaki. Namun, kencang di tempat lain. Semua pola dan warna kulit bisa dimiliki oleh mereka.",
                "Rp 3 jt - Rp 40 jt",
                "3 kg - 6 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed48,
                "Tonkinese",
                "Kucing Tonkinese berasal dari Amerika dari perkawinan silang kucing Siam dan Burmese. Maka dari itu, fisiknya menyerupai keduanya. Kucing yang populer tahun 1960-an ini termasuk ke keluarga oriental dengan karakteristik tubuh yang berukuran sedang dengan kepala bulat lembut dengan telinga lebar. Memiliki mata lebih terbuka daripada bentuk Oriental klasik biasanya dan berwarna hijau hingga biru muda. Tubuhnya kokoh, kencang dan berotot. Rambut kucing Tonkinese pendek dan terasa halus dan lembut. Wajah, telinga, kaki dan ekor lebih gelap dari tubuhnya dan menyatu dengan warna tubuh. Jenis kucing Tonkinese hadir dalam berbagai warna. Dengan kepribadiannya yang penyayang dan ramah, namun kucing ini cukup cerewet bersuara. ",
                "Rp 8 jt - Rp 14 jt",
                "2.5 kg - 5.5 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed49,
                "Turkish Angora",
                "Kucinga Anggora atau Turkish Angora adalah kucing yang berasal dari wilayah Ankara, Turki Tengah. Kucing ini memiliki rambut yang lembut dan halus dengan kerangka ramping dan bertulang halus. Ekornya penuh dan meruncing. Kucing ini merupakan jenis kucing yang penurut serta aktif.",
                "Rp 40 jt - Rp 100 jt",
                "3 kg - 7 kg"
            )
        )
        breedList.add(
            BreedCat(
                R.drawable.img_breed50,
                "Turkish Van",
                "Kucing Turkish Van adalah kucing besar, berotot, namun elegan dengan tubuh putih bersih dan kepala dengan ekor berwarna khas yang berasal dari Turki. Memiliki kesan anggun namun kuat, dengan telinga yang tinggi dan mata yang besar, oval, dan ekspresif. Turkish Van memiliki bulu semi-panjang yang lembut dan halus, bukan berbulu atau berbulu halus. Cakarnya berumbai, dan ekornya harus berupa bulu yang lebat. Mereka mungkin memiliki dua mata kuning, dua mata biru atau salah satu dari keduanya.",
                "Rp 11 jt - Rp 22 jt",
                "Jantan : 5 kg - 7 kg\n" +
                        "Betina : 5 kg - 6 kg"
            )
        )

        // Add more items with detailInfo, price, and weight
        return breedList
    }
}